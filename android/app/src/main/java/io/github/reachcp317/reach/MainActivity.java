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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener,
        OnMarkerDragListener, OnMapReadyCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener {

    private EditText r;
    private ImageButton Search;
    private ImageButton Add;
    private Button Logout;
    private DBConnect connect;
    private ArrayList<Integer> eventsList;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFLC;
    private double radius = 30;
    Location location;
    Event newEvent;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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
      
        mFLC = LocationServices.getFusedLocationProviderClient(this);
        location = new Location("");
        newEvent = new Event();
        eventsList = new ArrayList<Integer>();

        try {
            result = new Connect().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }

        try {
            mMap.setMyLocationEnabled(true);
        } catch (SecurityException e) {
            Toast.makeText(this, "App needs permissions", Toast.LENGTH_SHORT).show();
        }
    }
          
    public void onClick_plus(View v) {
        Intent intent = new Intent(MainActivity.this, CreateEventActivity.class);
        startActivity(intent);
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
        eventsList.clear();
        updateLocation();

        try {
            result = new UpdateEventsList().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, " " + eventsList.size(), Toast.LENGTH_SHORT).show();
        addMarkers();
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

    private class Connect extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
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
        //Toast.makeText(this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Loc Update", Toast.LENGTH_SHORT).show();
    }

    private class CreateEvent extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            connect.createEvent("test", "test", -80.527810, 43.474040, "test", "test", "test", 0);
            return null;
        }
    }

    private class UpdateEventsList extends AsyncTask<String, Void, String> {

        double lat = location.getLatitude();
        double lon = location.getLongitude();


        @Override
        protected String doInBackground(String... strings) {

            eventsList = connect.closeLocation(lat, lon, radius);

            return null;
        }
    }

    public void addMarkers() {
        MarkerOptions marker = new MarkerOptions();
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));

        mMap.clear();

        for (int i = 0; i < eventsList.size(); i++){
            //System.out.println(i);
            try {
                if (connect.con.isClosed()) {
                    try {
                        result = new Connect().execute().get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                result = new QueryEvent(i).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            marker.position(new LatLng(newEvent.getLocation().getLatitude(), newEvent.getLocation().getLongitude()));
            mMap.addMarker(marker);
        }
    }

    private class QueryEvent extends AsyncTask<String, Void, String> {
        int index = 0;

        public QueryEvent(int i) {
            super();
            this.index = i;
        }

        @Override
        protected String doInBackground(String... Strings) {
            newEvent = connect.queryEvent(eventsList.get(index));
            return null;
        }
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
}
