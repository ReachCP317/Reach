package com.reachcp317.reach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.reachcp317.reach.UserRepository;

/**
 * Prototype for web page direction, Event form input and Event info display
 * @author James Robertson
 *
 */
//@SessionAttributes("event")
@Controller
public class EventController implements WebMvcConfigurer{
	@Autowired
	EventRepository db;
	//default Event search radius size
	private static final int DEFAULT_RADIUS = 10;
	GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyCWUQSdJbU9l36D14gaSlw9MovZNacqaf4")
		    .build();
    
	/**
	 * Dashboard page. Loads a Google Map and populates it with nearby Event markers based on the user's
	 * radius preferences.
	 * If User is not logged in, redirect to the index page
	 * @return
	 */
	@GetMapping("/dashboard")
	public String dashboardPage(Model model, HttpSession httpSession, SearchRadius searchRadius) {
		
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
		
			model.addAttribute(httpSession);
			List<Event> events = db.getMapMarkers(DEFAULT_RADIUS, 43.4724, 80.5263);
			
			model.addAttribute("events", events);
			searchRadius.setRadius(DEFAULT_RADIUS);
			model.addAttribute(searchRadius);
			return "dashboard";
		}
	}
	
	/**
	 * Updates the Events Nearby list and Google Map display when the user submits a change
	 * to the Maximum Distance slider.
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/dashboard")
	public String updateRadius(Model model, HttpSession httpSession, SearchRadius searchRadius){
		model.addAttribute(httpSession);
		List<Event> events = db.getMapMarkers(searchRadius.getRadius().intValue(), 43.4724, 80.5263);
		
		model.addAttribute("events", events);
		model.addAttribute(searchRadius);

		return "dashboard";
	}
	
	/**
	 * View an Event profile given a Event ID
	 * @return
	 */
	@GetMapping("/event/{id}")
	public String eventProfile(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}
		Event event = db.getById(id);
		//redirect User if given Event does not exist
		if (event == null) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute(event);
			return "DisplayEvent";
		}
	}
	
	/**
	 * View User's current event
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/DisplayEvent")
	public String userEvent(Model model, Event event, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			//check if User has an Event in progress; if not, redirect User to create one
			event = db.getCurrentUserEvent((int) httpSession.getAttribute("userID"));
			if (event == null) {
				return "redirect:/createEvent";
			}else {
				model.addAttribute(event);
				return "DisplayEvent";
			}
		}
	}
	
	@GetMapping("/DisplayEvent/{id}")
	public String viewEvent(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/dashboard";
		}
		Event event = db.getById(id);
		if (event == null) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute(httpSession);
			model.addAttribute(event);
		}
		
		return "DisplayEvent";
	}
	
	@GetMapping("/createEvent")
	public String createEvent(Model model, Event event, HttpSession httpSession){
		if (httpSession.getAttribute("username") == null) {
			return "redirect:/index";
		}else if (db.getCurrentUserEvent((int) httpSession.getAttribute("userID")) != null) {
			return "redirect:/DisplayEvent";
		}
		else {
			return "createEvent";
		}
	}
	
	/**
	 * 
	 * @param event
	 * @param httpSession
	 * @return
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ApiException 
	 */
	@PostMapping("/createEvent")
	public String submitEvent(Event event, HttpSession httpSession) {
		//uses the Google Geocoding API to convert given address into latitude/longitude		
		GeocodingResult[] results;
		try {
			results = getLatLong(event.getAddress());
		} catch (Exception e) {
			return "createEvent";
		}
	
		if (results == null) {
			return "createEvent";
		}else {
			event.setLatitude(results[0].geometry.location.lat);
			event.setLongitude(results[0].geometry.location.lng);
			//date picker isn't working, temp fix; format = 2018-01-18 11:30:00
			event.setStartTime("2018-12-15 1:30:00");
			event.setEndTime("2018-12-20 1:30:00");
			int success = db.createEvent(event, (int) httpSession.getAttribute("userID"));
			if (success == 1) {
				return "DisplayEvent";	
			}else {
				return "createEvent";
			}
		}
	
	}
	
	/**
	 * Allows a User to edit their current Event
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/editEvent")
	public String editEvent(Model model, Event event, HttpSession httpSession) {
		if (httpSession.getAttribute("username") == null) {
			return "redirect:/index";
		}else if (db.getCurrentUserEvent((int) httpSession.getAttribute("userID")) == null) {
			return "redirect:/createEvent";
		}
		else {
			event = db.getCurrentUserEvent((int) httpSession.getAttribute("userID"));
			if (event == null) {
				return "createEvent";
			}else {
				model.addAttribute(event);
				model.addAttribute(httpSession);
				return "editEvent";	
			}
		}
	}
	
	/**
	 * Submit Event changes
	 * @param model
	 * @param event
	 * @param httpSession
	 * @return
	 */
	@PostMapping("/editEvent")
	public String submitEventEdit(Model model, Event event, HttpSession httpSession) {
		GeocodingResult[] results;
		try {
			results = getLatLong(event.getAddress());
		} catch (Exception e) {
			return "editEvent";
		}
	
		if (results == null) {
			return "editEvent";
		}else {
			event.setLatitude(results[0].geometry.location.lat);
			event.setLongitude(results[0].geometry.location.lng);
			//date picker isn't working, temp fix; format = 2018-01-18 11:30:00
			event.setStartTime("2018-12-15 1:30:00");
			event.setEndTime("2018-12-20 1:30:00");
			boolean success = db.updateEvent(event, (int) httpSession.getAttribute("userID"));
			if (success) {
				return "DisplayEvent";	
			}else {
				return "editEvent";
			}
		}
	}
	
	/**
	 * Checks if there is a valid User logged in to the app.
	 * @param httpSession
	 * @return true if User logged in, false otherwise
	 */
	//TODO: move to login controller and reference from there?
	private boolean checkSession(HttpSession httpSession) {
		boolean valid = true;
		if (httpSession.getAttribute("username") == null) {
			valid = false;
		}
		return valid;
	}

	/**
	 * Retrieves latitude and longitude values from the Google Geocoding API given an address
	 * @param address
	 * @return
	 * @throws ApiException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	private GeocodingResult[] getLatLong(String address) throws ApiException, InterruptedException, IOException {
		GeocodingResult[] results =  GeocodingApi.geocode(context,
				address).await();
		return results;
	}
}

