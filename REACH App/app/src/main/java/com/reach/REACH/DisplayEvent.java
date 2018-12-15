package com.reach.REACH;

import android.location.Address;
import android.location.Geocoder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class DisplayEvent extends AppCompatActivity {
    private TextView Days_Left;
    private TextView Description;
    private TextView Event_Name;
    private TextView Address;
    private Date Start_Date_And_Time;
    private Date End_Date_And_Time;
    private TextView Capacity;
    private Event e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        TextView Days_Left = (TextView)findViewById(R.id.daysLeft);
        TextView Description = (TextView)findViewById(R.id.description);
        TextView Event_Name = (TextView)findViewById(R.id.eventName);
        TextView Address = (TextView)findViewById(R.id.address);
        TextView Start_Date_And_Time = (TextView)findViewById(R.id.startDate);
        TextView End_Date_And_Time = (TextView)findViewById(R.id.endDate);
        TextView Capacity = (TextView)findViewById(R.id.capacity);
        Days_Left.setText(e.getDays());
        Description.setText(e.getDescription());
        Event_Name.setText(e.getName());
        Address.setText(e.getAddress());
        Start_Date_And_Time.setText((CharSequence) e.getStartTime());
        End_Date_And_Time.setText((CharSequence)e.getEndTime());
        Capacity.setText(e.getCapacity());

    }
}
