package io.github.reachcp317.reach;

import java.util.*;

/**
 * 
 */
public class MainActivity {

	/**
	 * Default constructor
	 */
	public MainActivity() {
	}

	/**
	 * A list of events
	 */
	private ArrayList<Event> eventList;

	/**
	 * ?
	 */
	private GoogleMap mMap;

	/**
	 * Change for web implmentation
	 * @param savedInstance
	 */
	protected void onCreate(void savedInstance) {
		// TODO implement here
	}

	/**
	 * Called when the map is ready to be used. Overriden to call updateEventsList(), setMyLocationEnabled(). Change for web implmentation
	 * @param googleMap
	 */
	public void onMapReady(GoogleMap googleMap) {
		// TODO implement here
	}

	/**
	 * Called when a marker is clicked/tapped. This will be overridden to go to the EventActivity. Change for web implmentation
	 * @param marker 
	 * @return
	 */
	public boolean onMarkerClick(Marker marker) {
		// TODO implement here
		return false;
	}

	/**
	 * Change for web implmentation
	 * @param marker
	 */
	public void onMarkerDragEnd(Marker marker) {
		// TODO implement here
	}

	/**
	 * Iterate through the list of events in the database, storing only nearby locations (small difference in lat/long) into eventsList. For each event, if distanceTo(Location) returns a value (in meters, will have to convert) less than or equal to the user's radius, a Marker will be placed via addMarker().
	 */
	public void updateEventList() {
		// TODO implement here
	}

	/**
	 * Sets the user's radius to whatever value in kilometers was entered in the text box.
	 * @param radius
	 */
	public void setRadius(float radius) {
		// TODO implement here
	}

	/**
	 * Called when the 'Create Event' button is pressed. Places a draggable marker on the user's location. Once the marker has been confirmed to be in the correct position, opens the EventView.
	 */
	public void createEvent() {
		// TODO implement here
	}

}