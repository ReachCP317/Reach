package com.reachcp317.reach;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.reachcp317.reach.UserRepository;

/**
 * Prototype for web page direction, user form input and user info display
 * @author Morgenne Besenschek
 *
 */

//TODO: should login stuff be its own controller?
@SessionAttributes("user")
@Controller
public class UserController implements WebMvcConfigurer{
	//test variable
	public boolean login = false;
	//public User currentUser; ?
	@Autowired
	UserRepository db;

	/**
	 * When User visits the site, redirects them based on if they are logged in or not
	 * @return
	 */
	@GetMapping("/")
	public String mainPage() {
		if (!login) {
			return "index";
		}else {
			return "dashboard";
		}
		
	}
	
	/**
	 * Dashboard page. If User is not logged in, redirect to the 
	 * @return
	 */
	@GetMapping("/dashboard.html")
	public String dashboardPage() {
		login = true;
		if (login) {
			return "dashboard";
		}else {
			return "login";
		}
	}
	
	/**
	 * User login page
	 * @param user
	 * @return
	 */
	@GetMapping("/login")
	//TODO: use a more secure login method? Spring Security?
	public String loginPage(User user) {
		/**
		 * Database connection test
		 */
		List<User> users = db.viewAllUsernames();
		for (Iterator<User> iter = users.iterator(); iter.hasNext();) {
			User current = iter.next();
			System.out.println("User: " + current.getUsername());
		}
		
		System.out.println("Password = " + db.getPasswordTest());
		
		return "login";
	}
	
	/**
	 * Verifies if User information entered is valid. If it is, login User and redirect to the dashboard.
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/dashboard.html")
	public String checkLoginInfo(User user, Model model) {
		//TODO: properly check if user has entered a username or email. Is @ or . permitted in usernames?
		if (user.getUsername().contains("@")) {
			System.out.println("This is an email");
			//verify user info
			//retrieve username using email
		}else {
			System.out.println("This is a username.");
			//if db
		}
		System.out.println("Login successful");
		System.out.println("User: " + user.getUsername() + " " + user.getPassword());
		//model.addAttribute(user);
		return "dashboard";
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/createAccount")
	public String createAccount(User user) {
		return "createAccount";
	}
	
	@PostMapping("/login")
	public String checkNewAccount(User user) {
		//test variable
		boolean valid = true;
		if (!valid) {
			return "createAccount";
		}else {
			return "login";
		}
		
	}
	
	/*
	@PostMapping("/profile/{id}")
	public String userProfile() {
		
		return "profile";
	}
	*/
	
	/**
	 * View a User profile given a User id
	 * @return
	 */
	//TODO: A bit more research into how this works
	@GetMapping("/profile/{id}")
	public String userProfile() {
		
		return "profile";
	}
	
	//@PostMapping("")
	
}
