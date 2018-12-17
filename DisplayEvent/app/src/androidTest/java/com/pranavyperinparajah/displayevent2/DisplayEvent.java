package com.pranavyperinparajah.createeventpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);


        Intent Info = getIntent();
        //get the stored info & set it to a variable
        // check eventname, daysleft and address first
        String eventname = Info.getStringExtra("eventName");
        TextView eventName = (TextView) findViewById(R.id.eventName);
        eventName.setText(eventname);

        String daysleft = Info.getStringExtra("daysLeft");
        TextView daysLeft = (TextView) findViewById(R.id.eventName);
        eventName.setText(daysleft);

        String addressfield = Info.getStringExtra("address");
        TextView address = (TextView) findViewById(R.id.address);
        eventName.setText(addressfield);

        // action for when button clicked on create page
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v) {
                launchActivity();

            }
        });
    }
    private void launchActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
