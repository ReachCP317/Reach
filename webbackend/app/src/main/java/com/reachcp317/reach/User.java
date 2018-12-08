package com.reachcp317.reach;

import java.util.*;

/**
 * Object to hold user information
 * @author Morgenne Besenschek (implementation), James Robertson (UML export)
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
	 * A user's username.
	 */
	private String username;

	/**
	 * a user's date of birth.  
	 * Uses java class java.util.Date for date.
	 */
	private Date dateOfBirth;

	/**
	 * The users email.
	 */
	private String email;

	/**
	 * The users password.
	 */
	private String password;

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
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;

	}

	/**
	 * Gets the user ID from the database and returns it as in int.
	 * @return The user ID as an int.
	 */
	/*
	public int getID() {
		// TODO implement here
		return 0;
	}
	*/

	/**
	 * Gets a users rating from the database and returns it as a float.
	 * @return The users rating as a float
	 */
	public float getRating() {
		// TODO implement here
		return this.rating;
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
		return this.username;
	}

	/**
	 * Gets the users phone number from the database and returns it as a String.
	 * @return The users phone number.
	 */
	public String getPhoneNumber() {
		// TODO implement here
		return this.phoneNumber;
	}

	/**
	 * Gets the users gender from the database and returns it as a String.
	 * @return The gender of the user.
	 */
	public String getGender() {
		// TODO implement here
		return this.gender;
	}

	/**
	 * Gets the user's password from the database and returns it as a string.
	 * @return The users password.
	 */
	public String getPassword() {
		// TODO implement here
		return this.password;
	}
	
		/**
	 * Gets the user object from the database based upon the userID.
	 * @param userID The users ID
	 * @return The users object.
	 */
	public User getUser(int userID) {
		// TODO implement here
		User user = new User();
		//code to get the user from DB
		return user;
	}

	/**
	 * Sets the rating of a user in the database.
	 * @param Parameter1 The new user rating.
	 */
	public void setRating(float rating) {
		/* todo: how do we want to calculate ratings?
		 * Do we keep track of how many times a user has been rated and set a new rating based on
		 * the overall average of the ratings? Is database handling this?
		 */
		this.rating = rating;
	}

	/**
	 * Sets the username of the user in the database.
	 * @param username The new username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the user's phone number in the database.
	 * @param phoneNumber The new phone number for the user.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Sets the user's password in the database
	 * @param password The new user password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the user's email in the database
	 * @param email The new email for the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the user's gender in the database
	 * @param gender The new value for the user's gender.
	 */
	public void setGender(String gender) {
		this.gender = gender;
		
	}

}