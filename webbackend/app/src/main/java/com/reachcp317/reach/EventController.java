package com.reachcp317.reach;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
	//default Event search radius size; declared as an object for Thymeleaf compatibility
	private static final int DEFAULT_RADIUS = 10;
    
	/**
	 * Dashboard page. Loads a Google Map and populates it with nearby Event markers based on the user's
	 * radius preferences.
	 * If User is not logged in, redirect to the index page
	 * @return
	 */
	@GetMapping("/dashboard")
	public String dashboardPage(Model model, HttpSession httpSession, SearchRadius searchRadius) {
		/**
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
		**/
			model.addAttribute(httpSession);
			List<Event> eventList = new ArrayList<Event>();
			//List<Event> events = db.getMapMarkers();
			/**
			for (Iterator<Event> iter = events.iterator(); iter.hasNext();) {
				Event current = iter.next();
				System.out.println("Event: " + current.getEventID() + ", Location: (" +
				current.getLatitude() + ", " + current.getLongitude() + "), Address: " +
						current.getAddress());
			}
			**/
			Event e = new Event(123, 123);
			e.setLatitude(43.4670);
			e.setLongitude(-80.5220);
			
			eventList.add(e);
			System.out.println(eventList.get(0).getLatitude());
			
			Event e2 = new Event(124, 124);
			e2.setLatitude(43.4680);
			e2.setLongitude(-80.5230);
			eventList.add(e2);
			
			//Event[] events = eventList.toArray(new Event[eventList.size()]);
			//System.out.println("Event 1: " + events[0].getLatitude());
			//System.out.println("Event 2: " + events[1].getLatitude());
			
			System.out.println("e2 id = " + e2.getEventID());
			model.addAttribute("events", eventList);
			searchRadius.setRadius(DEFAULT_RADIUS);
			model.addAttribute(searchRadius);
			//model.add
			return "dashboard";
		//}
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
		List<Event> eventList = new ArrayList<Event>();
		//List<Event> events = db.getMapMarkers();
		/**
		for (Iterator<Event> iter = events.iterator(); iter.hasNext();) {
			Event current = iter.next();
			System.out.println("Event: " + current.getEventID() + ", Location: (" +
			current.getLatitude() + ", " + current.getLongitude() + "), Address: " +
					current.getAddress());
		}
		**/
		Event e = new Event(123, 123);
		e.setLatitude(43.4670);
		e.setLongitude(-80.5220);
		eventList.add(e);
		System.out.println(eventList.get(0).getLatitude());
		
		Event e2 = new Event(124, 124);
		e2.setLatitude(43.4680);
		e2.setLongitude(-80.5230);
		eventList.add(e2);
		
		//Event[] events = eventList.toArray(new Event[eventList.size()]);
		//System.out.println("Event 1: " + events[0].getLatitude());
		//System.out.println("Event 2: " + events[1].getLatitude());
		
		System.out.println("Radius = " + searchRadius.getRadius());
		model.addAttribute("events", eventList);
		model.addAttribute(searchRadius);

		return "dashboard";
	}
	
	/**
	 * View an Event profile given a Event ID
	 * @return
	 */
	@GetMapping("/event/{id}")
	public String eventProfile(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		Event event = db.getById(id);
		//TODO: Proper error page?
		if (event == null) {
			return "redirect:/dashboard";
		}else {
			System.out.println(id);
			model.addAttribute(event);
			return "DisplayEvent";
		}
	}
	
	/**
	 * Redirects user to dashboard
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/event")
	public String test(HttpSession httpSession) {

		/**
		 * Database connection test
		 */
		/**
		List<Event> events = db.viewAllEventnames();
		for (Iterator<Event> iter = events.iterator(); iter.hasNext();) {
			Event current = iter.next();
			System.out.println("Event: " + current.getEventID());
		}
		**/
		
		return "DisplayEvent";
	}
	
	@GetMapping("/createEvent")
	public String createEvent(Event event, HttpSession httpSession){
		return "createEvent";
	}
	
	@PostMapping("/createEvent")
	//TODO: how do I parse the results of the Google Maps geocoding?
	//TODO: implement Google Maps Java Geocoding files?
	public String submitEvent(Event event, HttpSession httpSession){
		return "createEvent";
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
	
}

