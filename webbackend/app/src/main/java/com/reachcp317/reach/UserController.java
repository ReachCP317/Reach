package com.reachcp317.reach;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.reachcp317.reach.UserRepository;

/**
 * Controller for web page direction, user form input and user info display
 * @author Morgenne Besenschek
 *
 */

@Controller
public class UserController implements WebMvcConfigurer{
	@Autowired
	UserRepository db;

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
	 * @author Morgenne Besenschek
	 * @param user
	 * @param model
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	@PostMapping("/login")
	//TODO: cookie functionality
	public String checkLoginInfo(User user, Model model, HttpSession httpSession) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		user.setPassword(hashPassword(user.getPassword()).toString());
		
		//returns valid User ID if login info matches a User in the database
		int valid = db.verifyLogin(user);
		if (valid > 0) {
			httpSession.setAttribute("username", user.getUsername());
			httpSession.setAttribute("userID", valid);
			model.addAttribute(httpSession);
			return "redirect:/dashboard";
		}
		
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
		//password and confirm password boxes did not match
		if (user.getPassword().compareTo(user.getPasswordConfirm()) != 0) {
			return "redirect:/createAccount";
		}else {
			//returns a valid User ID if the account is made
			int createSuccess = db.createUser(user);
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
		if (user == null) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute(httpSession);
			model.addAttribute(user);
			return "profile";
		}
	}
	
	/**
	 * Visiting profile without any id number defaults to the logged in user's profile.
	 * @return
	 */
	@GetMapping("/profile")
	public String profile(Model model, HttpSession httpSession) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			//retrieve logged in User's profile
			User user = db.getById((int) httpSession.getAttribute("userID"));
			model.addAttribute(httpSession);
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
	public String editProfile(HttpSession httpSession, User user, Model model) {
		if (!checkSession(httpSession)) {
			return "redirect:/index";
		}else {
			user = db.getById((int) httpSession.getAttribute("userID"));
			model.addAttribute(httpSession);
			model.addAttribute(user);
			return "ProfilePage_edit";
		}
	}
	
	@PostMapping("/editProfile")
	public String submitEditProfile(HttpSession httpSession, User user) throws NoSuchAlgorithmException {
		if (user.getPasswordConfirm().compareTo(user.getPasswordUpdate()) != 0) {
			return "ProfilePage_edit";
		}else {
			user.setPassword(hashPassword(user.getPassword()).toString());
			boolean success = db.updateUser(user, (int) httpSession.getAttribute("userID"));
			if (!success) {
				return "ProfilePage_edit";
			}else {
				return "profile";
			}
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
	
	/**
	 * Password hash; credit to Michael Pintur (SHA256 algorithm)
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private StringBuffer hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest mDigest;
		StringBuffer sb = new StringBuffer();
		mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(password.getBytes());
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb;
	}
}
