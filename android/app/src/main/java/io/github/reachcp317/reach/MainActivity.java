package io.github.reachcp317.reach;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener{

    private FusedLocationProviderClient mFLC;
    private ArrayList<Integer> eventsIDList;
    private GoogleMap mMap;
    private User mUser;
    private Event tempEvent;
    private LatLng tempLL;
    private DBConnect connect = new DBConnect();
    private double radius;

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
        } catch (SecurityException e){
            // do something if location permissions are denied. Toast is placeholder
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG).show();
        }
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        mMap.setOnMyLocationButtonClickListener(this);
        updateEventsList();
    }

    @Override
    public boolean onMyLocationButtonClick(){
        updateEventsList();
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
                        eventsIDList = connect.closeLocation(location.getLatitude(),location.getLongitude(),0.5);     // retrieve ArrayList of all eventIDs with locations of <0.5 difference in latitude and longitude from database.
                        for (int i = 0; i < eventsIDList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User's radius
                            tempEvent = connect.queryEvent(eventsIDList.get(i));
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < radius) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        }
                        eventsIDList.clear();
/*                              //This block is for testing purposes only.
                        eventsList.add(new Event("Event1","Address1",37.47,-122.2));
                        eventsList.add(new Event("Event2","Address2",37.5,-122.0));
                        eventsList.add(new Event("Event3","Address3",37.4,-122.1));
                        for (int i = 0; i < eventsList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User's radius
                            tempEvent = eventsList.get(i);
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < 5) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        } eventsList.clear();*/
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
