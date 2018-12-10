package com.reachcp317.reach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
 *		This is a basic unit-testing class to test the Event class methods
 *			(specifically the setters and getters)
 */
		
public class EventTest {
	public Event evnt;
	
	public String name = "testEvent";
	public String description = "description";
	public String address = "address";
	public Double longitude = 30.0;
	public Double latitude = 31.0;
		/*Dates are assigned in the setUp function */
	public int capacity = 20;
	public ArrayList<String> eventType = null;
	public int userID = 999999999;
	
	@BeforeTest
	public void setUp() throws ParseException{ //using dateformat parse requires try/catch or throwing an exception
		evnt = new Event();

		// Convert string to date
        SimpleDateFormat dateformat1 = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strdate1 = "20-01-2019 08:00:00"; //8am to 8pm jan 20th 2019
        String strdate2 = "20-01-2019 20:00:00";
        
        //Assigning dates
        Date date1 = dateformat1.parse(strdate1);
        Date date2 = dateformat1.parse(strdate2);
        
        //create an event object to test using the additional constructor
        //note ArrayList<String> eventType is given a null value here - this should not stay that way
        evnt.Event(name, description,address, longitude, latitude,
				date1, date2, capacity, null, userID);

	}
	
	/*
	 * check each getter (and eventually each setter) in Event
	 */
	@Test
	public void getName_Returns_name(){
		//Should also use setter when it's functional
		//Arrange
		final String expected = name;
		
		//Act
		final String actual = evnt.getName();
		
		//Assert
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void getDescription_Returns_Description() {
		final String expected = description;
		final String actual = evnt.getDescription();
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void getAddress_Returns_Address() {
		final String expected = address;
		final String actual = evnt.getDescription();
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void getCapacity_Returns_Capacity() {
		final int expected = capacity;
		final int actual = evnt.getCapacity();

		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void getUserID_Returns_userID() {
		final int expected = userID;
		final int actual = evnt.getUserID();

		Assert.assertEquals(actual, expected);
	}
	
	/*TODO add more tests as Event makes progress*/
	
	@AfterTest
	public void tearDown() {
		evnt = null;
	}
	
}
