package io.github.reachcp317.reach;

import android.location.Location;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


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
                        eventsIDList.clear();
                        mMap.clear();
                        eventsIDList = connect.closeLocation(location.getLatitude(),location.getLongitude(),0.5);     // retrieve ArrayList of all eventIDs with locations of <0.5 difference in latitude and longitude from database.
                        for (int i = 0; i < eventsIDList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User's radius
                            tempEvent = connect.queryEvent(eventsIDList.get(i));
                            if (location.distanceTo(tempEvent.getLocation()) < radius) {
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                            }
                        }
                    } else { // location object null if device's location is off, Google Play services on device restarted, or device is new/factory reset. These should be rare cases
                        // i'm actually not sure what to put here
                    }
                }
            });
        } catch (SecurityException se){
            // do something if location permissions are denied. Toast is placeholder
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG).show();
        }
    }

}
