package com.reach.REACH;
/**
 * @author Midusa Nadeswarathasan
 *  * nade1680@mylaurier.ca
 */
/**
 * @author Julius Fan
 * fanx0430@mylaurier.ca
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayEvent extends AppCompatActivity {
    private Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_event);
        Intent intent = getIntent();
        Bundle e = intent.getExtras();

        TextView Name = findViewById(R.id.name);
        TextView Description = findViewById(R.id.description);
        TextView Address = findViewById(R.id.addressView);
        TextView Start_Date_And_Time = findViewById(R.id.startDate);
        TextView End_Date_And_Time = findViewById(R.id.endDate);
        TextView totalInterested = findViewById(R.id.totalInterested);
        Description.setText(e.getString("DESC"));
        Name.setText(e.getString("NAME"));
        Address.setText(e.getString("ADDRESS"));
        Start_Date_And_Time.setText(e.getString("START"));
        End_Date_And_Time.setText(e.getString("END"));
        totalInterested.setText(Integer.toString(e.getInt("TOTIN")));

        Logout = (Button)findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DisplayEvent.this,Login_Activity.class));
                Toast.makeText(DisplayEvent.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

