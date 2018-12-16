package com.reach.REACH;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author Midusa Nadeswarathasan
 *  * nade1680@mylaurier.ca
 */

public class UIActivity extends AppCompatActivity {
    private Button Edit;
    private boolean success = true;
    private Button Logout;
    public View UIView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_ui,container,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        Intent intent = this.getIntent();
        Bundle u = intent.getExtras();

        if (intent != null) {
            TextView name = findViewById(R.id.name);
            TextView userName = findViewById(R.id.description);
            TextView  userPassword = findViewById(R.id.addressView);

            name.setText(u.getString("NAME"));
            userName.setText(u.getString("USERNAME"));
            userPassword.setText(u.getString("PASSWORD"));
        }
        Logout = (Button)findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(UIActivity.this,Login_Activity.class));
                Toast.makeText(UIActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }
    }
