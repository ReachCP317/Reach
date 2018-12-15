package com.reach.REACH;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, AdapterView.OnItemClickListener {
    Integer[] radius = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private User mUser;
    private FusedLocationProviderClient mFLC;
    private ArrayList<Integer> eventsIDList;
    private Event tempEvent;
    private LatLng tempLL;
    private DBConnect connect = new DBConnect();
    private ArrayList<Event> eventsList = new ArrayList<>();   //For testing purposes only
    private Geocoder geocoder;
    private List<Address> addressList;
    private Marker tempMarker;
    private Integer r;
    private ImageButton Search;
    private ImageButton Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Search = (ImageButton) findViewById(R.id.Search);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                onClick_search(v);
        }
    });
        Add = (ImageButton)findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onClick_plus(v);
            }
        });

    }

    public void onClick_search(View v) {
        Spinner spinner = (Spinner) findViewById(R.id.simpleSpinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, radius);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        try {
            mMap.setMyLocationEnabled(true);
            mFLC.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        mMap.clear();
                        eventsIDList = connect.closeLocation(location.getLatitude(), location.getLongitude(), 0.5);     // retrieve ArrayList of all eventIDs with locations of <0.5 difference in latitude and longitude from database.
                        for (int i = 0; i < eventsIDList.size(); i++) { // iterate through eventsIDList and displays them on the map as Markers if the distance is within User.java's radius
                            //tempEvent = connect.queryEvent(eventsIDList.get(i));
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < r) {      // convert distance to kilometers, check if within radius
                                tempLL = new LatLng(tempEvent.getLocation().getLatitude(), tempEvent.getLocation().getLongitude());
                                tempMarker = mMap.addMarker(new MarkerOptions().position(tempLL).title(tempEvent.getName()));
                                tempMarker.setTag(tempEvent);
                            }
                        }
                        eventsIDList.clear();
                       //This block is for testing purposes only.
                        eventsList.add(new Event("Event1","Address1",37.47,-122.2));
                        eventsList.add(new Event("Event2","Address2",37.5,-122.0));
                        eventsList.add(new Event("Event3","Address3",37.4,-122.1));
                        for (int i = 0; i < eventsList.size(); i++){ // iterate through eventsIDList and displays them on the map as Markers if the distance is within User.java's radius
                            tempEvent = eventsList.get(i);
                            if ((location.distanceTo(tempEvent.getLocation()) / 1000) < r) {      // convert distance to kilometers, check if within radius
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
            // do something if location permissions are denied. Toast is placeholder
            Toast.makeText(getApplicationContext(), "Please turn on device location through Settings > Location", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_plus(View v) {
        Intent intent = new Intent(MainActivity.this, CreateEvent.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            buildGoogleAPIClient();

            mMap.setMyLocationEnabled(true);
        }

    }

    public boolean checkUserLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (googleApiClient == null) {
                            buildGoogleAPIClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Turn on Location Services", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    protected synchronized void buildGoogleAPIClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        if (currentUserLocationMarker != null) {
            currentUserLocationMarker.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("user Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if (googleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    public class YourItemSelectedListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), radius[position], Toast.LENGTH_LONG).show();
            r = (Integer) parent.getItemAtPosition(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}

