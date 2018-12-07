import java.util.*;

import com.sun.xml.internal.bind.v2.model.core.NonElement;

import io.github.reachcp317.reach.User;

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
	private String address;

	/**
	 * The capacity of the event.
	 */
	private int capacity;

	/**
	 * 
	 */
	private ArrayList<String> eventType;

	/**
	 * The ID of the user who is hosting/creating the party.
	 */
	private int userID;

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
	public void Event(String name, String description, String address, double latitiude, double longitude, Date startTime, Date endTime, int capacity, ArrayList<String> eventType, int userID) {
		// TODO implement here
		this.name = name;
		this.description = description;
		this.address = address;
		this.latitiude = latitiude;
		this.longitude = longitude;
		this.startTime = startTime;
		this.endTime = endTime;
		this.capacity = capacity;
		this.eventType = eventType;
		this.userID = userID;
		DBConnect dbc = new DBConnect();
		
		dbc.createEvent(this.userID, this.description, this.longitude, this.laititude, this.startTime.toLocaleString(), this.endTime.toLocaleString(), this.address, this.capacity);
	}

	/**
	 * Gets the name of an event.
	 * @return The name of the event.
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
	 * Gets the capacity of the event from the database and returns it as a int.
	 * @return The capacity of the event.
	 */
	public int getCapacity() {
		// TODO implement here
		return 0;
	}

	/**
	 * Gets the event type from the database.
	 * @return The event type in a string array
	 */
	public ArrayList<String> getEventType() {
		// TODO implement here
		return null;
	}

	/**
	 * Gets the event orginizers user ID from the database and returns it as a int.
	 * @return The ID of the user who is hosting/creating the party.
	 */
	public int getUserID() {
		// TODO implement here
		return 0;
	}

	/**
	 * Gets the event ID from the database
	 * @return the event ID to be returned.
	 */
	public int getEventID() {
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
	public void setAddress(String address) {
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
	public void setTotallnterested(int totallnterested) {
		// TODO implement here
	}

	/**
	 * Sets the capacity of the event in the database.
	 * @param Parameter1 The capacity of the event.
	 */
	public void setCapacity(int Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the event type in the database.
	 * @param Parameter1 A string array filled with event types.
	 */
	public void setEventType(ArrayList<String> Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the ID of the user who is hosting/creating the party in the database.
	 * @param Parameter1 The ID of the user who is hosting/creating the party.
	 */
	public void setUserID(int Parameter1) {
		// TODO implement here
	}

	/**
	 * Sets the event ID in the database.
	 * @param Parameter1 the new id for the event.
	 */
	public void setEventID(int Parameter1) {
		// TODO implement here
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