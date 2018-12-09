package com.reachcp317.reach;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.reachcp317.reach.UserRepository;

/**
 * Prototype for web page direction, user form input and user info display
 * @author Morgenne Besenschek
 *
 */

//TODO: Should login go in its own Controller?
//TODO: Fix return statements to adhere to standards
//@SessionAttributes("user")
@Controller
public class UserController implements WebMvcConfigurer{
	//test variable
	public boolean login = false;
	//public User currentUser; ?
	@Autowired
	UserRepository db;
	//@Autowired
	//TODO:

	/**
	 * When User visits the site, redirects them based on if they are logged in or not
	 * @return
	 */
	@GetMapping("/")
	public String redirectMain(HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/index")
	public String mainPage(HttpSession httpSession) {
		if (checkSession(httpSession)) {
			return "redirect:/dashboard";
		}else {
			return "index";
		}
	}
	
	/**
	 * Dashboard page. If User is not logged in, redirect to the index page
	 * @return
	 */
	@GetMapping("/dashboard")
	public String dashboardPage(Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			model.addAttribute(httpSession);
			System.out.println("Does this Work?" + httpSession.getAttribute("username"));
			return "dashboard";
		}
	}
	
	/**
	 * User login page. If User is logged in, redirect to the dashboard page.
	 * @param user
	 * @return
	 */
	@GetMapping("/login")
	public String loginPage(User user, HttpSession httpSession) {
		if (checkSession(httpSession)) {
			return "redirect:/dashboard";
		}
		/**
		 * Database connection test
		 */
		/**
		List<User> users = db.viewAllUsernames();
		for (Iterator<User> iter = users.iterator(); iter.hasNext();) {
			User current = iter.next();
			System.out.println("User: " + current.getUsername() + ", ID: " + current.getID());
		}
		
		System.out.println("Password = " + db.getPasswordTest());
		**/
		
		return "login";
	}
	
	/**
	 * Verifies if User information entered is valid. If it is, login User and redirect to the dashboard.
	 * @param user
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	//TODO: cookie functionality
	public String checkLoginInfo(User user, Model model, HttpSession httpSession) {
		//TODO: properly check if user has entered a username or email. Is @ or . permitted in our usernames?
		if (user.getUsername().contains("@")) {
			System.out.println("This is an email");
			//verify user info
			//retrieve username using email
		}else {
			System.out.println("This is a username.");
			//if db
		}
		
		//user.setPassword(encrypt.encode(user.getPassword()));
		
		//int valid = db.verifyLogin(user);
		
		httpSession.setAttribute("username", user.getUsername());
		httpSession.setAttribute("userID", user.getID());
		
		System.out.println("User = " + httpSession.getAttribute("username"));
		System.out.println("Login successful");
		System.out.println("User: " + user.getUsername() + " " + user.getPassword());
		
		model.addAttribute(httpSession);
		//model.addAttribute(user);
		return "redirect:/dashboard";
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping("/createAccount")
	public String createAccount(User user) {
		return "createAccount";
	}
	
	/**
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
	**/
	
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
	@GetMapping("/profile/{id}")
	public String userProfile(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		User user = db.getById(id);
		//TODO: Proper error page?
		//if (user == null) {
		//	return "dashboard";
		//}else {	
			//System.out.println("User: " + user.getUsername());
		System.out.println(id);
		model.addAttribute(user);
		return "profile";
		//}
	}
	
	@GetMapping("/profile")
	/**
	 * Visiting profile without any id number defaults to the logged in user's profile.
	 * @return
	 */
	public String profile(Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			//retrieve logged in User's profile
			return "profile";
		}
	}
	

	/**
	@PostMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "index";
	}
	**/
	
	/**
	 * Logs out the current User, returns User to main page.
	 * @param httpSession
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "index";
	}
	
	//**HELPER METHODS**
	
	/**
	 * Checks if there is a valid User logged in to the app.
	 * @param httpSession
	 * @return true if User logged in, false otherwise
	 */
	private boolean checkSession(HttpSession httpSession) {
		boolean valid = true;
		if (httpSession.getAttribute("username") == null) {
			valid = false;
		}
		return valid;
	}
	
}
