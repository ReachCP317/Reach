package com.reachcp317.reach;

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
    
	/**
	 * View an Event profile given a Event ID
	 * @return
	 */
	@GetMapping("/event/{id}")
	public String eventProfile(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		Event event = new Event();
		//TODO: Proper error page?
		if (event == null) {
			return "redirect:/dashboard";
		}else {
			System.out.println(id);
			model.addAttribute(event);
			return "profile";
		}
	}
	
	@GetMapping("/event")
	public String test() {

		/**
		 * Database connection test
		 */
		List<Event> events = db.viewAllEventnames();
		for (Iterator<Event> iter = events.iterator(); iter.hasNext();) {
			Event current = iter.next();
			System.out.println("Event: " + current.getEventID());
		}
		
		return "DisplayEvent";
	}
	
}

