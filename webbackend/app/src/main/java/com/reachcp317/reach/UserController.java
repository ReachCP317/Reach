package com.reachcp317.reach;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

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
	 * User login page. If User is logged in, redirect to the dashboard page.
	 * @param user
	 * @return
	 */
	@GetMapping("/login")
	public String loginPage(User user, HttpSession httpSession) {
		if (checkSession(httpSession)) {
			return "redirect:/dashboard";
		}
		
		return "login";
	}
	
	/**
	 * Verifies if User information entered is valid. Immediately hashes password upon submission.
	 * If User info is valid, login User and redirect to the dashboard.
	 * @author Morgenne Besenschek, Michael Pintur (SHA256 algorithm)
	 * @param user
	 * @param model
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	@PostMapping("/login")
	//TODO: cookie functionality
	//https://docs.oracle.com/javaee/6/api/index.html?javax/servlet/http/HttpSession.html
	public String checkLoginInfo(User user, Model model, HttpSession httpSession) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		//TODO: properly check if user has entered a username or email. Is @ or . permitted in our usernames?
		if (user.getUsername().contains("@")) {
			System.out.println("This is an email");
			//verify user info
			//retrieve username using email
		}else {
			System.out.println("This is a username.");
			//if db
		}
		
		//hashes password before comparing with the database
		MessageDigest mDigest;
		StringBuffer sb = new StringBuffer();
		mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(user.getPassword().getBytes());
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		user.setPassword(sb.toString());
		
		
		//returns valid User ID if login info matches a User in the database
		int valid = db.verifyLogin(user);
		System.out.println("Result: " + valid);
		if (valid > 0) {
			httpSession.setAttribute("username", user.getUsername());
			//TODO: should I be mapping user ID?
			httpSession.setAttribute("userID", valid);

			System.out.println("User = " + httpSession.getAttribute("username"));
			System.out.println("Login successful");
			System.out.println("User: " + user.getUsername() + " " + user.getPassword());

			model.addAttribute(httpSession);
			return "redirect:/dashboard";
		}
		//TODO: tell User what was wrong
		else {
			return "redirect:/login";
		}
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
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/createAccount")
	//TODO: Proper error handling, save username in box if a user creation error occurs
	public String checkNewAccount(User user, HttpSession httpSession) {
		System.out.println("User = " + user.getUsername());
		//password and confirm password boxes did not match
		if (user.getPassword().compareTo(user.getPasswordConfirm()) != 0) {
			//TODO: should I be dummying out the passwords here for security purposes?
			return "redirect:/createAccount";
		}else {
			//returns a valid User ID if the account is made
			int createSuccess = db.createUser(user);
			//boolean createSuccess = true;
			if (createSuccess <= 0) {
				return "redirect:/createAccount";
			}
			else{
				httpSession.setAttribute("username", user.getUsername());
				httpSession.setAttribute("userID", createSuccess);
				return "redirect:/dashboard";
			}

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
	@GetMapping("/profile/{id}")
	public String userProfile(@PathVariable(value = "id") int id, Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/dashboard";
		}
		User user = db.getById(id);
		//TODO: Proper error page?
		if (user == null) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute(httpSession);
			model.addAttribute(user);
			return "profile";
		}
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
			User user = db.getById((int) httpSession.getAttribute("userID"));
			model.addAttribute(user);
			return "profile";
		}
	}
	
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
	
	@GetMapping("/editProfile")
	public String editProfile(HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			return "ProfilePage_edit";
		}
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
