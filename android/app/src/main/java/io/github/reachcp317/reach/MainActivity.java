package io.github.reachcp317.reach;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMarkerDragListener, OnMapReadyCallback {

    private ArrayList<Event> eventsList;
    private GoogleMap mMap;
    private float radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    /**
     * Creates a new event and adds it to the database.
     */
    public void createEvent() {

    }

    /**
     * Sets a new radius used for searching events.
     *
     * @param radius radius to search for events in.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Updates eventsList from the database.
     */
    public void updateEventsList() {

    }
}

