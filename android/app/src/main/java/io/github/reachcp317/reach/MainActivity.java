package io.github.reachcp317.reach;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.Address;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {

    private GoogleMap mMap;
    private User mUser;
    private double radius;
    private FusedLocationProviderClient mFLC;
    private ArrayList<Integer> eventsIDList;
    private Event tempEvent;
    private LatLng tempLL;
    //private DBConnect connect = new DBConnect();
    private ArrayList<Event> eventsList = new ArrayList<>();   //For testing purposes only
    private Marker tempMarker;
    private Geocoder geocoder;
    private List<Address> addressList;


    @Override
    public boolean onMarkerClick(Marker marker) {
        // Opens the event.
        Bundle eventData = new Bundle();
        Intent intent = new Intent(MainActivity.this,DisplayEventActivity.class);
        Event e = (Event) marker.getTag();
        eventData.putString("NAME",e.getName());
        eventData.putString("ADDRESS",e.getAddress());
        eventData.putString("DESC",e.getDescription());
        eventData.putInt("TOTIN",e.getTotalInterested());
        eventData.putString("START",e.getStartTime().toString());
        eventData.putString("END",e.getEndTime().toString());
        intent.putExtras(eventData);
        startActivity(intent);
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // mUser = ???      Fetch user data from database
    }

    /**
     * @author Julius Fan
     * fanx0430@mylaurier.ca
     */

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        try {
            mMap.setMyLocationEnabled(true);
            this.radius = 20;
        } catch (SecurityException e){
            // do something if location permissions are denied. Toast is placeholder
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG).show();
        }
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick(){
        updateEventsList();
        /*Intent intent = new Intent(MainActivity.this,CreateEventActivity.class);
        startActivity(intent);*/
        return false; // Return false so default behaviour occurs (camera moves to user's current position)
    }

    /**
     * @author Julius Fan
     * fanx0430@mylaurier.ca
     */

    public void updateEventsList(){
        try {
            mMap.setMyLocationEnabled(true);
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.clear();
/*                        eventsIDList = connect.closeLocation(location.getLatitude(),location.getLongitude(),0.5);     // retrieve ArrayList of all eventIDs with locations of <0.5 difference in latitude and longitude from database.
                        for (int i = 0; i < eventsIDList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User's radius
                            tempEvent = connect.queryEvent(eventsIDList.get(i));
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < mUser.getRadius()) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        }
                        eventsIDList = null;*/
                          //This block is for testing purposes only.
                        eventsList.add(new Event("Event1", "Sample description 1","Address1",new Date(),new Date(), 37.47,-122.2, 12));
                        eventsList.add(new Event("Event2", "Sample description 2","Address2",new Date(),new Date(), 37.5,-122.0, 9));
                        eventsList.add(new Event("Event3", "Sample description 3","Address3",new Date(),new Date(), 37.4,-122.1, 7));
                        eventsList.add(new Event("Event4","Sample description 4", "Address4", new Date(), new Date(),43.47,-80.53,11));
                        eventsList.add(new Event("Event5","Sample description 5", "Address5", new Date(), new Date(),43.5,-80.53,11));
                        eventsList.add(new Event("Event6","Sample description 6", "Address6", new Date(), new Date(),43.48,-80.51,11));
                        for (int i = 0; i < eventsList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User's radius
                            tempEvent = eventsList.get(i);
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < radius) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        } eventsList.clear();

                    } else { // location object null if device's location is off, Google Play services on device restarted, or device is new/factory reset. These should be rare cases
                        Toast.makeText(getApplicationContext(), "Null Location", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } catch (SecurityException se){
            // do something if location permissions are denied. Toast is placeholder
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG).show();
        }
    }

}


