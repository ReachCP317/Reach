package com.reachcp317.reach;

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
    
    @GetMapping("/event")
    public String createEventForm(Model model){
        model.addAttribute("event", new Event());
        System.out.print("1");
        return "event";
    }
    @PostMapping("/event")
    public String createEventSubmit(@ModelAttribute Event event){
        System.out.print("2");
        return "result";
    }
    @GetMapping("/createEvent")
    public String goToCreateEvent(Event event){
        System.out.print("3");
        return "createEvent";
    }
}
