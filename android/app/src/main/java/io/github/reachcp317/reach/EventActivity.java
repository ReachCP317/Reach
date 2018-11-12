package io.github.reachcp317.reach;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 *
 * need to import event class
 *
 */

public class EventActivity extends FragmentActivity {

    private Event currentEvent;

    void interested() {
		/*
		 *
		 * Called when the user presses the 'Interested' button. Increments the Event's
		 * "totalInterested" value by 1. When the button is pressed again, decrement by 1.
		 *
		 * dustin's suggested algorithm:
		 *
		 * if [user's interested list does not contain currentEvent.getID] then
		 *     event.setTotalInterested(event.getTotalInterested + 1)
		 * else
		 *     event.setTotalInterested(event.getTotalInterested - 1)
		 *
		 * This would require:
		 * - interested (linked list) attribute for user class
		 * - ID attribute for event class
		 * - getID method for event class
		 *
		 */
    }

    void setEvent(Event event) {
        // this method has no description in the design doc/chart and is probably too vague
    }

    void deleteEvent() {
        /*
		 *
         * Called when the 'Delete Event' button is pressed. Clears all event information and
         * removes the event from the database.
		 *
		 * dustin's suggested algorithm
		 *
		 * if [currentEvent is not null] then
		 *     [delete currentEvent from database]
		 *     [set currentEvent to null]
		 *
         */
    }

    void updateEvent() {
        /*
            Submits changes to the event's information to the database.
        */
    }

    void changeLocation() {
        /*
            Opens MainActivity and makes the event's Marker draggable. Once the marker has been
            confirmed to be in the correct position, opens the EventView.
        */
    }

    void giveRating(float rating) {
        /*
            Called when the user presses the 'Rate' button. Calculates the new rating of the
            event's creator and updates it to the database.
        */
    }
}