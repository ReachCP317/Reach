package io.github.reachcp317.reach;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMarkerDragListener, OnMapReadyCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener {

    private DBConnect connect;
    private ArrayList<Integer> eventsList;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFLC;
    private double radius = 10;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        location = new Location("");
        eventsList = new ArrayList<Integer>();
        new Connect().execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            Toast.makeText(this, "App needs permissions", Toast.LENGTH_SHORT).show();
        }

        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(-33.852, 151.211));
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        googleMap.addMarker(marker);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        new Connect().execute();
        new CreateEvent().execute();
        new Connect().execute();
        //eventsList.clear();
        updateLocation();
        new UpdateEventsList().execute();
        addMarkers();
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

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public boolean onMyLocationButtonClick() {

        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setOnMyLocationButtonClickListener(this);

                try {
                    mMap.setMyLocationEnabled(true);
                } catch (SecurityException e) {
                    Toast.makeText(this, "App needs permissions", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * Sets a new radius used for searching events.
     *
     * @param radius radius to search for events in.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    private class Connect extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            connect = new DBConnect();
            return null;
        }
    }

    public void updateLocation() {
        try {
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    MainActivity.this.location = location;
                }
            });
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Loc Update", Toast.LENGTH_SHORT).show();
    }

    private class CreateEvent extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            connect.createEvent("test", "test", -80.527810, 43.474040, "test", "test", "test", 0);
            return null;
        }
    }

    private class UpdateEventsList extends AsyncTask<Void, Void, Void> {

        double lat = location.getLatitude();
        double lon = location.getLongitude();


        @Override
        protected Void doInBackground(Void... voids) {

            eventsList = connect.closeLocation(lat, lon, radius);

            return null;
        }
    }

    public void addMarkers() {
        Event newEvent;
        MarkerOptions marker;

        Toast.makeText(this, "Adding " + eventsList.size() + " markers", Toast.LENGTH_SHORT);

        //mMap.clear();

        for (int i = 0; i < eventsList.size(); i++){
            newEvent = connect.queryEvent(eventsList.get(i));
            marker = new MarkerOptions();
            marker.position(new LatLng(newEvent.getLocation().getLatitude(), newEvent.getLocation().getLongitude()));
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
            mMap.addMarker(marker);
        }
    }
}

