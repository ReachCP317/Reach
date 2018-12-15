package com.reachcp317.reach;


import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

/**
 * 
 * @author Michael Child-Wynne-Jones
 * 
 *	chil5440@mylaurier.ca
 *
 *		This is a basic unit-testing class to test the User class methods
 *			(specifically the setters and getters)
 */

public class UserTest {
	/*
	 * 
	private int id;
	private float rating;
	private String username;
	private Date dateOfBirth;
	private String email;
	private String password;
	private String passwordConfirm;
	private String gender;
	private String phoneNumber;
	*/
	
	public int id = 999999999;
	public String username = "default";
	public String email = "default@default.ca";
	public String password = "1234";
	
	public User user;
	
	@BeforeTest
	public void setUp() {
		user = new User(id,username,email,password);
		
	}
	@Test
	public void getID_Returns_id() {
		final int expected = id;
		final int actual = user.getID();
		
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void getUsername_Returns_username() {
		final String expected = username;
		final String actual = user.getUsername();
		
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void getEmail_Returns_email() {
		final String expected = email;
		final String actual = user.getEmail();
		
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void getPassword_Returns_Password(){
		final String expected = password;
		final String actual = user.getPassword();
		
		Assert.assertEquals(actual, expected);
	}
	
	
	@AfterTest
	public void tearDown() {
		user = null;
	}
}
