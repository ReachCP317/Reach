package io.github.reachcp317.reach;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMapReadyCallback{

    private GoogleMap mMap;
    private User mUser;
    private FusedLocationProviderClient mFLC;
    private ArrayList<Integer> eventsIDList;
    private Event tempEvent;
    private LatLng tempLL;
    //private DBConnect connect = new DBConnect();
    private ArrayList<Event> eventsList = new ArrayList<>();   //For testing purposes only
    private Marker tempMarker;
    private EditText r;
    private int radius;
    private ImageButton Search;
    private ImageButton Add;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Search = findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onClick_search(v);
            }
        });
        Add = findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClick_plus(v);
            }
        });
        Logout = findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,Login_Activity.class));
                Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Searches for nearby events within user's radius based on current location
     * @author Julius Fan
     */
    public void onClick_search(View v) {
        r = findViewById(R.id.radius);
        if(!r.getText().toString().equals("")) {
            radius = Integer.parseInt(r.getText().toString());
        } else {
            radius = 0;
        }
        try {
            mMap.setMyLocationEnabled(true);
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.clear();
                        /*eventsIDList = connect.closeLocation(location.getLatitude(), location.getLongitude(), 0.5);     // retrieve ArrayList of all eventIDs with locations of <0.5 difference in latitude and longitude from database.
                        for (int i = 0; i < eventsIDList.size(); i++) { // iterate through eventsIDList and displays them on the map as Markers if the distance is within User.java's radius
                            //tempEvent = connect.queryEvent(eventsIDList.get(i));
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < radius) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        }
                        eventsIDList.clear();*/
                        //This block is for testing purposes only.
                        eventsList.add(new Event("Event1", "Sample description 1","Address1",new Date(),new Date(), 37.47,-122.2, 12));
                        eventsList.add(new Event("Event2", "Sample description 2","Address2",new Date(),new Date(), 37.5,-122.0, 9));
                        eventsList.add(new Event("Event3", "Sample description 3","Address3",new Date(),new Date(), 37.4,-122.1, 7));
                        eventsList.add(new Event("Event4","Sample description 4", "Address4", new Date(), new Date(),43.47,-80.53,11));
                        eventsList.add(new Event("Event5","Sample description 5", "Address5", new Date(), new Date(),43.5,-80.53,11));
                        eventsList.add(new Event("Event6","Sample description 6", "Address6", new Date(), new Date(),43.48,-80.51,11));
                        for (int i = 0; i < eventsList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User.java's radius
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
        } catch (SecurityException se) {
            Toast.makeText(getApplicationContext(), "Please give REACH location permissions through Settings", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_plus(View v) {
        Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        mMap.setOnMarkerClickListener(this);
    }

    /**
     * Starts DisplayEventActivity, displaying event data attached to the Marker
     * @author Julius Fan
     */
    @Override
    public boolean onMarkerClick(Marker marker) {
        Bundle eventData = new Bundle();
        Intent intent = new Intent(MainActivity.this, DisplayEventActivity.class);
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

}

