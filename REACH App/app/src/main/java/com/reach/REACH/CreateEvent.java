package com.reach.REACH;

import android.location.Address;
import android.location.Geocoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CreateEvent extends AppCompatActivity {
    private int Days_Left;
    private String Description;
    private String Event_Name;
    private EditText Address;
    private String Start_Date_And_Time;
    private String End_Date_And_Time;
    private Button Create;
    private int Capacity;
    private Event e;
    private Geocoder geocoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        Create =(Button) findViewById(R.id.button);
        Create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Days_Left = Integer.parseInt(String.valueOf((EditText)findViewById(R.id.daysLeft)));
                Description = ((EditText)findViewById(R.id.description)).toString();
                Event_Name = ((EditText)findViewById(R.id.eventName)).toString();
                Address = (EditText)findViewById(R.id.address);
                Start_Date_And_Time = ((EditText)findViewById(R.id.startDate)).toString();
                End_Date_And_Time = ((EditText)findViewById(R.id.endDate)).toString();
                Capacity = Integer.parseInt(String.valueOf((EditText)findViewById(R.id.capacity)));
                String field = Address.getText().toString();
                List<Address> location = null;
                try {
                    location = geocoder.getFromLocationName(field,1);
                    Address local = location.get(0);
                    e = new Event(Event_Name, Address.toString(),local.getLatitude() , local.getLongitude());
                    e.setDescription(Description);
                    e.setDays(Days_Left);
                    e.setStartTime(Start_Date_And_Time);
                    e.setEndTime(End_Date_And_Time);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }
}