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
	 * 
	 */
	private Event currentEvent;

	/**
	 * Called when the user presses the 'Interested' button. Increments the Event's "totalInterested" value by 1. When the button is pressed again, decrement by 1.
	 */
	public void interested() {
		// TODO implement here
	}

	/**
	 * @param event
	 */
	public void setEvent(Event event) {
		// TODO implement here
	}

	/**
	 * Called when the 'Delete Event' button is pressed. Clears all event information and removes the event from the database.
	 */
	public void deleteEvent() {
		// TODO implement here
	}

	/**
	 * Submits changes to the event's information to the database.
	 */
	public void updateEvent() {
		// TODO implement here
	}

	/**
	 * Opens MainActivity and makes the event's Marker draggable. Once the marker has been confirmed to be in the correct position, opens the EventView.
	 */
	public void changeLocation() {
		// TODO implement here
	}

	/**
	 * Called when the user presses the 'Rate' button. Calculates the new rating of the event's creator and updates it to the database.
	 * @param rating
	 */
	public void giveRating(float rating) {
		// TODO implement here
	}

}