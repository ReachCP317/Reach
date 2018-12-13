package com.reachcp317.reach;

import java.util.*;

//import com.sun.xml.internal.bind.v2.model.core.NonElement;

import com.reachcp317.reach.User;

/**
 * 
 */
public class Event {

	/**
	 * Default constructor
	 */
	public Event() {
	}

	/**
	 * The id of the event
	 */
	private int eventID;

	/**
	 * Name of event.
	 */
	private String name;

	/**
	 * Descripton of event.
	 */
	private String description;

	/**
	 * Latititude of event.
	 */
	private double latitude;

	/**
	 * Longitude of event.
	 */
	private double longitude;

	/**
	 * The time the event begins.
	 */
	private Date startTime;

	/**
	 * The time the event ends.
	 */
	private Date endTime;

	/**
	 * The total number of users interested in going to the event.
	 * Not being used
	 */
	private int totallnterested;

	/**
	 * The events street number.
	 */
	private String address;

	/**
	 * The capacity of the event.
	 */
	private int capacity;

	/**
	 * The type of event?
	 */
	private String eventType;

	/**
	 * The ID of the user who is hosting/creating the party.
	 */
	private int userID;
	/**
	 * The range the event wants to reach
	 */
	private double eventRange;
	
	int days;

	/**
	 * The constructor for the Event class.
	 * @param name 
	 * @param description 
	 * @param address 
	 * @param latitiude 
	 * @param longitude 
	 * @param startTime 
	 * @param endTime 
	 * @param capacity the capacity of the event.
	 * @param eventType A string array of event types.
	 * @param userID The ID of the user who is hosting/creating the party.
	 */
	public Event(String name, String description, String address, double latitude, double longitude, Date startTime, Date endTime, int capacity, String eventType, int userID, double eventRange) {
		// TODO implement here
		this.name = name;
		this.description = description;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.eventType = eventType;
		this.userID = userID;
		this.eventRange = eventRange;

	}
	/**
	 * Basic Event object initialization
	 * @param id
	 */
	public Event(int eventID, int userID) {
		this.eventID = eventID;
		this.userID = userID;
	}
	/**
	 * Gets the name of an event.
	 * @return The name of the event.
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * Gets the address of an event
	 * @return
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * Gets the description of the event
	 * @return
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Gets the latitude of the event.
	 * @return
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 * Gets the longitude of the event.
	 * @return
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 * Gets the time the event begins.
	 * @return
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Gets the time the event ends.
	 * @return
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Gets the total number of users interested in going to the event.
	 * @return
	 */
	public int getTotallnterested() {
		return this.totallnterested;
	}

	/**
	 * Gets the capacity of the event from the database and returns it as a int.
	 * @return The capacity of the event.
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Gets the event type from the database.
	 * @return The event type in a string array
	 */
	public String getEventType() {
		return this.eventType;
	}

	/**
	 * Gets the event orginizers user ID from the database and returns it as a int.
	 * @return The ID of the user who is hosting/creating the party.
	 */
	public int getUserID() {
		return this.userID;
	}

	/**
	 * Gets the event ID from the database
	 * @return the event ID to be returned.
	 */
	public int getEventID() {
		return this.eventID;
	}

	/**
	 * Gets the event range
	 * @return 
	 */
	public double getEventRange() {
		return this.eventRange;
	}
	
	public int getDays() {
		return this.days;
	}

	/**
	 * Sets the range of the event.
	 * @param name
	 */
	public void setEventRange(double eventRange) {
		this.eventRange = eventRange;
	}

	/**
	 * Sets the name of the event.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description of the event
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the address of the event.
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Sets the latitude of the address
	 * @param latitiude
	 */
	public void setLatitude(double latitiude) {
		this.latitude = latitiude;
	}

	/**
	 * Sets the longitude of the address
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Sets the start time of the event.
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the end time of the event
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Sets the total number of interested users.
	 * @param totallnterested
	 */
	public void setTotallnterested(int totallnterested) {
		this.totallnterested = totallnterested;
	}

	/**
	 * Sets the capacity of the event in the database.
	 * @param capacity The capacity of the event.
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * Sets the event type in the database.
	 * @param eventType A string array filled with event types.
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	public void setDays(int days) {
		this.days = days;
	}


	/**
	 * Provides details on an event.
	 * @param eventID The ID of the event.
	 * @return 
	 */
	public String eventDetails(int eventID) {
		// TODO implement here
		return "";
	}

}