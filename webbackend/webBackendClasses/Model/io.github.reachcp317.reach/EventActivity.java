package io.github.reachcp317.reach;

import java.util.*;

/**
 * 
 */
public class EventActivity {

	/**
	 * Default constructor
	 */
	public EventActivity() {
	}

	/**
	 * A list of events
	 */
	private ArrayList<Event> eventList;

	/**
	 * 
	 */
	private int radius;

	/**
	 * Called when the user presses the 'Interested' button. Increments the Event's "totalInterested" value by 1. When the button is pressed again, decrement by 1.
	 * @param Parameter1 The event whose interested number is being increased.
	 */
	public void interested(Event Parameter1) {
		// TODO implement here
	}

	/**
	 * Called when the 'Delete Event' button is pressed. Clears all event information and removes the event from the database.
	 * @param Parameter1 THe event to be deleted from the database.
	 */
	public void deleteEvent(Event Parameter1) {
		// TODO implement here
	}

	/**
	 * Called when the user presses the 'Rate' button. Calculates the new rating of the event's creator and updates it to the database.
	 * @param rating
	 */
	public void giveRating(float rating) {
		// TODO implement here
	}

	/**
	 * Iterate through the list of events in the database, storing only nearby locations (small difference in lat/long) into eventsList.
	 */
	public void updateEventList() {
		// TODO implement here
	}

	/**
	 * Sets the user's radius to whatever value in kilometers was entered.
	 * @param radius
	 */
	public void setRadius(float radius) {
		// TODO implement here
	}

	/**
	 * Gets the users set radius from the frontend?
	 * @param Parameter1
	 */
	public void getRadius(void Parameter1) {
		// TODO implement here
	}

	/**
	 * Updates the valuse for a given event with new values.
	 * @param event the event to be updated
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
	public void eventUpdate(Event event, String name, String description, String address, double latitiude, double longitude, Date startTime, Date endTime, int capacity, String array eventType, String userID) {
		// TODO implement here
	}

	/**
	 * Provides an ArrayList of Event objects
	 * @return A list of Event objects that are with in the desired radius
	 */
	public ArrayList<Event> getEvents() {
		// TODO implement here
		return null;
	}

	/**
	 * Compares the password given by the user to the password in the database. Returns a false if wrong and true if correct.
	 * @param userName The user name of the user whos password is being checked.
	 * @param givenPassword 
	 * @return True if the password is correct and False if it is not a match in the database.
	 */
	public boolean passwordCompare(String userName, String givenPassword) {
		// TODO implement here
		return false;
	}

}