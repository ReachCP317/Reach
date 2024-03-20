package io.github.reachcp317.reach;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayEventActivity extends AppCompatActivity {

    /**
     * Displays event details
     * @author Julius Fan
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayevent);
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
    }
}

