package io.github.reachcp317.reach;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {

    private Button CreateButton;
    private Geocoder geocoder;
    private boolean success = true;

    /**
     * Activity to allow user input in order to upload an event to the database
     * @author Julius Fan
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createevent);
        CreateButton = findViewById(R.id.button);
        CreateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    String Description = (findViewById(R.id.description)).toString();
                    String Event_Name = (findViewById(R.id.name)).toString();
                    String Address = (findViewById(R.id.address)).toString();
                    String Start_Date_And_Time = (findViewById(R.id.startDate)).toString();
                    String End_Date_And_Time = (findViewById(R.id.endDate)).toString();

                    geocoder = new Geocoder(CreateEventActivity.super.getApplicationContext(),Locale.getDefault());
                    List<Address> addressList = geocoder.getFromLocationName(Address, 1);
                    Address tempad = addressList.get(0);
                    /* Database functionality
                    DBConnect connect = new DBConnect();
                    connect.createEvent("email",Description,tempad.getLongitude(),tempad.getLatitude(),Start_Date_And_Time,End_Date_And_Time,Address);*/

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error submitting event", Toast.LENGTH_LONG).show();
                    success = false;
                }
                if (success) {
                    Toast.makeText(getApplicationContext(), "Event uploaded!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
