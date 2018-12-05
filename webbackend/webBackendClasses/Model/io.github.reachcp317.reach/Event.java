package io.github.reachcp317.reach;

import java.util.*;

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
	private double laititude;

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
	 */
	private int totallnterested;

	/**
	 * The events street number.
	 */
	private Address address;

	/**
	 * The constructor for the Event class.
	 * @param name 
	 * @param description 
	 * @param address 
	 * @param latitiude 
	 * @param longitude 
	 * @param startTime 
	 * @param endTime
	 */
	public void Event(String name, String description, Address address, double latitiude, double longitude, Date startTime, Date endTime) {
		// TODO implement here
	}

	/**
	 * Gets the name of an event.
	 * @return
	 */
	public String getName() {
		// TODO implement here
		return "";
	}

	/**
	 * Gets the description of the event
	 * @return
	 */
	public String getDesciption() {
		// TODO implement here
		return "";
	}

	/**
	 * Gets the latitude of the event.
	 * @return
	 */
	public double getLatitude() {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * Gets the longitude of the event.
	 * @return
	 */
	public double getLongitude() {
		// TODO implement here
		return 0.0d;
	}

	/**
	 * Gets the time the event begins.
	 * @return
	 */
	public Date getStartTime() {
		// TODO implement here
		return null;
	}

	/**
	 * Gets the time the event ends.
	 * @return
	 */
	public Date getEndTime() {
		// TODO implement here
		return null;
	}

	/**
	 * Gets the total number of users interested in going to the event.
	 * @return
	 */
	public int getTotallnterested() {
		// TODO implement here
		return 0;
	}

	/**
	 * Sets the name of the event.
	 * @param name
	 */
	public void setName(String name) {
		// TODO implement here
	}

	/**
	 * Sets the description of the event
	 * @param description
	 */
	public void setDesciption(String description) {
		// TODO implement here
	}

	/**
	 * Sets the address of the event.
	 * @param address
	 */
	public void setAddress(Address address) {
		// TODO implement here
	}

	/**
	 * Sets the latitude of the address
	 * @param latitiude
	 */
	public void setLatitude(double latitiude) {
		// TODO implement here
	}

	/**
	 * Sets the longitude of the address
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		// TODO implement here
	}

	/**
	 * Sets the start time of the event.
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		// TODO implement here
	}

	/**
	 * Sets the end time of the event
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		// TODO implement here
	}

	/**
	 * Sets the total number of interested users.
	 * @param totallnterested
	 */
	public void setTotallnterested(void totallnterested) {
		// TODO implement here
	}

}