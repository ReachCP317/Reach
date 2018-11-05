package io.github.reachcp317.reach;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient mFLC;
    private ArrayList<Event> eventsList = new ArrayList<Event>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFLC = LocationServices.getFusedLocationProviderClient(this);
        try {
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        updateEventsList();
                    } else { // location object null if device's location is off, Google Play services on device restarted, or device is new/factory reset. These should be rare cases
                        // i'm actually not sure what to put here
                    }
                }
            });
        } catch (SecurityException se){
            // popup message saying "this app requires permissions"
        }
    }

    public void updateEventsList(){
        eventsList.clear();
        // retrieve all locations with <0.5 difference in latitude and longitude from database.
    }

}
