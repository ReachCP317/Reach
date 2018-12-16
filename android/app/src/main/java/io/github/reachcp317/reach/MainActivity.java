package io.github.reachcp317.reach;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMarkerDragListener, OnMapReadyCallback, OnMyLocationButtonClickListener {

    private DBConnect connect;
    private ArrayList<Integer> eventsList;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFLC;
    private float radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect = new DBConnect();
        Toast.makeText(this, connect.toString(), Toast.LENGTH_SHORT).show();
        if (connect == null) {
            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show();
        }
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
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
        updateEventsList();
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
    public void updateEventsList(){
        connect.createEvent("test", "test", 0, 0, "test", "test", "test", 0);
    }
}

