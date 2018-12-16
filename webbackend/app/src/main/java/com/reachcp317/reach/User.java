package com.reachcp317.reach;

import java.util.*;

/**
 * Object to hold user information
 * @author Morgenne Besenschek (implementation), James Robertson (UML export)
 *
 */
//TODO: Clean up, figure out what actually needs to be here, what actually needs setters, etc.
//TODO: Should User be split? UserLogin + UserInfo? hmm
public class User {

	/**
	 * Default constructor
	 */
	public User() {
		
	}

	/**
	 * A user's identification number, assigned by the database	
	 */
	private int id;

	/**
	 * A user's rating
	 */
	private float rating;

	/**
	 * A user's username.
	 */
	private String username;

	/**
	 * The users email.
	 */
	private String email;

	/**
	 * The users password.
	 */
	private String password;
	
	private String passwordConfirm;
	
	private String passwordUpdate;

	/**
	 * The constructor for the User class.
	 * @param username The users username.
	 * @param email 
	 * @param password The users password.
	 */
	public User(int id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;

	}
	
	/**
	 * Basic User object initialization
	 * @param id
	 */
	public User(int id) {
		this.id = id;
	}

	/**
	 * Gets the user ID from the database and returns it as in int.
	 * @return The user ID as an int.
	 */
	
	public int getID() {
		return this.id;
	}

	/**
	 * Gets the user's rating
	 * @return The user's rating as a float
	 */
	public float getRating() {
		// TODO implement here
		return this.rating;
	}

	/**
	 * Gets the user's username
	 * @return The user's username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Gets the user's email
	 * @return The user's email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Gets the user's password
	 * @return The user's password.
	 */
	public String getPassword() {
		// TODO implement here
		return this.password;
	}
	
	public String getPasswordConfirm() {
		return this.passwordConfirm;
	}
	
	public String getPasswordUpdate() {
		return this.passwordUpdate;
	}

	/**
	 * Sets the rating of a user
	 * @param Parameter1 The new user rating.
	 */
	public void setRating(float rating) {
		/* TODO: how do we want to calculate ratings?
		 * Do we keep track of how many times a user has been rated and set a new rating based on
		 * the overall average of the ratings? Is database handling this?
		 */
		this.rating = rating;
	}

	/**
	 * Sets the username of the user
	 * @param username The new username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Sets the user's password
	 * @param password The new user password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Sets the user's email
	 * @param email The new email for the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public void setPasswordUpdate(String passwordUpdate) {
		this.passwordUpdate = passwordUpdate;
	}

}