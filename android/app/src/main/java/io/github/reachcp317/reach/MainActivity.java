package io.github.reachcp317.reach;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import io.github.reachcp317.reach.Event;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener{

    private FusedLocationProviderClient mFLC;
    private ArrayList<Event> eventsList = new ArrayList<Event>();
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap = googleMap;
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        mMap.setOnMyLocationButtonClickListener(this);
        updateEventsList();
    }

    @Override
    public boolean onMyLocationButtonClick(){
        updateEventsList();
        return false; // Return false so default behaviour occurs (camera moves to user's current position)
    }

    public void updateEventsList(){
        try {
            mMap.setMyLocationEnabled(true);
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        eventsList.clear();
                        // retrieve all locations with <0.5 difference in latitude and longitude from database.
                    } else { // location object null if device's location is off, Google Play services on device restarted, or device is new/factory reset. These should be rare cases
                        // i'm actually not sure what to put here
                    }
                }
            });
        } catch (SecurityException se){
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG);
        }
    }

}
