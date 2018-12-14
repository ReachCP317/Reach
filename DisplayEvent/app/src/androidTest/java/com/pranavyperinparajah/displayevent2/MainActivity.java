package com.pranavyperinparajah.createeventpage;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get userinput; first try all string
        final String eventName = getResources().getString(R.string.Event_Name);
        final String daysLeft = getResources().getString(R.string.Days_Left);
        final String description = getResources().getString(R.string.Description);
        final String address = getResources().getString(R.string.Address);
        final String startDate = getResources().getString(R.string.Start_Date_And_Time);
        final String endDate = getResources().getString(R.string.End_Date_And_Time);
        final String capacity = getResources().getString(R.string.Capacity);

        // when button done clicked, send that info to the display page
        final Button done = (Button) findViewById(R.id.button);
        done.setOnClickListener( new View.OnClickListener ()
        {
            public void onClick (View v) {
                Intent sendInfo = new Intent(MainActivity.this, DisplayEvent.class);
                sendInfo.putExtra("nameEvent", eventName);
                sendInfo.putExtra("daysLeft", daysLeft);
                sendInfo.putExtra("description", description);
                sendInfo.putExtra("address", address);
                sendInfo.putExtra("startDate", startDate);
                sendInfo.putExtra("endDate", endDate);
                sendInfo.putExtra("capacity", capacity);
                startActivity(sendInfo);
            }
        });
    }


}

