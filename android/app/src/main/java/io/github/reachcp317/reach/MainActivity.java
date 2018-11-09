package io.github.reachcp317.reach;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

/* TO DO:
    need to import event class
    need to import googlemap class
    need to import marker class
*/

public class MainActivity extends FragmentActivity {

    private ArrayList<Event> eventsList;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    @Override
    protected void onMapReady(GoogleMap mMap) {
        // updateEventsList(), setMyLocationEnabled(
    }
    */

    protected boolean onMarkerClick(Marker marker) {
        /*
            Called when a marker is clicked/tapped. This will be overridden to go to the
            EventActivity.
        */
    }

    protected void onMarkerDragEnd(Marker marker) {
        //
    }

    public void updateEventsList() {
        /*
            Iterate through the list of events in the database, storing only nearby locations
            (small difference in lat/long) into eventsList. For each event, if distanceTo(Location)
            returns a value (in meters, convert to km) less than or equal to the user's radius, a
            Marker will be placed via addMarker().
        */
    }

    public void setRadius() {
        /*
            Sets the user's radius to whatever value in kilometers was entered in the text box.
        */
    }

    public void createEvent() {
        /*
            Called when the 'Create Event' button is pressed. Places a draggable marker on the
            user's location. Once the marker has been confirmed to be in the correct position,
            opens the EventView.
        */
    }
}