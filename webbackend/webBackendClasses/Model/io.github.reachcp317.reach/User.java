package io.github.reachcp317.reach;

import java.util.*;

/**
 * 
 */
public class User {

	/**
	 * Default constructor
	 */
	public User() {
	}

	/**
	 * A users identification number
	 */
	private int id;

	/**
	 * A users rating
	 */
	private float rating;

	/**
	 * A users username.
	 */
	private String username;

	/**
	 * a useres date of birth.  Used java class
	 * java.util.Date for date.
	 */
	private Date dateOfBirth;

	/**
	 * The users email.
	 */
	private String email;

	/**
	 * The users password.
	 */
	private string password;

	/**
	 * 
	 */
	private String gender;

	/**
	 * 
	 */
	private String phoneNumber;

	/**
	 * The constructor for the User class.
	 * @param username The users username.
	 * @param email 
	 * @param password The users password.
	 */
	public void User(String username, String email, String password) {
		// TODO implement here
	}

	/**
	 * Gets the user ID from the database and returns it as in int.
	 * @return The user ID as an int.
	 */
	public int getID() {
		// TODO implement here
		return 0;
	}

	/**
	 * Gets a users rating from the database and returns it as a float.
	 * @return The users rating as a float
	 */
	public float getRating() {
		// TODO implement here
		return 0.0f;
	}

	/**
	 * Gets the age of the user from the database and returns it is an int.
	 * @return
	 */
	public int getAge() {
		// TODO implement here
		return 0;
	}

	/**
	 * Get the users username for the database, if not found return string "please sign in first".
	 * @return The users username, if not found return a string containing "please sign in first".
	 */
	public String getUsername() {
		// TODO implement here
		return "";
	}

	/**
	 * Gets the users phone number from the database and returns it as a String.
	 * @return The users phone number.
	 */
	public String getPhoneNumber() {
		// TODO implement here
		return "";
	}

	/**
	 * Gets the users gender from the database and returns it as a String.
	 * @return The gender of the user.
	 */
	public String getGender() {
		// TODO implement here
		return "";
	}

	/**
	 * Gets the users password from the database and returns it as a stiring.
	 * @return The users password.
	 */
	public String getPassword() {
		// TODO implement here
		return "";
	}

	/**
	 * Sets the rating of a user in the database.
	 * @param Parameter1 The new user rating.
	 */
	public void setRating(float Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the username of the user in the database.
	 * @param Parameter1 The new username.
	 */
	public void setUsername(String Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the users phone number in the database.
	 * @param Parameter1 The new phone number for the user.
	 */
	public void setPhoneNumber(String Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the  users password in the database
	 * @param Parameter1 The new user password.
	 */
	public void setPassword(String Parameter1) {
		// TODO implement here
	}

	/**
	 * Set the users email in the database
	 * @param Parameter1 The new email for the user.
	 */
	public void setEmail(String Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the users gender in the database
	 * @param Parameter1 The new value for the users gender.
	 */
	public void setGender(String Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets a value for the users password in the database.
	 * @param Parameter1 The users new password.
	 */
	public void setPassword(String Parameter1) {
		// TODO implement here
	}

}