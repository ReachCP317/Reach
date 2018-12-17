package com.reach.REACH;


import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
/**
 * @author Midusa Nadeswarathasan
 *  * nade1680@mylaurier.ca
 */
/**
 * @author Julius Fan
 * fanx0430@mylaurier.ca
 */

public class CreateEvent extends AppCompatActivity {

    private Button CreateButton;
    private Geocoder geocoder;
    private boolean success = true;
    private Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        CreateButton = findViewById(R.id.button);
        CreateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    String Description = (findViewById(R.id.description)).toString();
                    String Event_Name = (findViewById(R.id.name)).toString();
                    String Address = (findViewById(R.id.address)).toString();
                    String Start_Date_And_Time = (findViewById(R.id.startDate)).toString();
                    String End_Date_And_Time = (findViewById(R.id.endDate)).toString();

                    geocoder = new Geocoder(CreateEvent.super.getApplicationContext(),Locale.getDefault());
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
        Logout = (Button)findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateEvent.this,Login_Activity.class));
                Toast.makeText(CreateEvent.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

