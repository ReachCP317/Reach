package com.reach.REACH;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
/**
 * @author Midusa Nadeswarathasan
 *  * nade1680@mylaurier.ca
 */

public class UIEditActivity extends AppCompatActivity {
    private Button Save;
    private boolean success;
    private Button Logout;
    public View UIEditView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.activity_ui,container,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiedit);
        Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UIEditActivity.this, UIActivity.class));
                Toast.makeText(UIEditActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                try {
                    String name = (findViewById(R.id.description)).toString();
                    String userName = (findViewById(R.id.name)).toString();
                    String userPassword = (findViewById(R.id.address)).toString();

                    } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error submitting changes", Toast.LENGTH_LONG).show();
                    success = false;
                    }
                    if (success) {
                        Toast.makeText(getApplicationContext(), "User uploaded!", Toast.LENGTH_LONG).show();
                        finish();
                        }
                    }
                });
        Logout = (Button)findViewById(R.id.Logout);
        Logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(UIEditActivity.this,Login_Activity.class));
                Toast.makeText(UIEditActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
            }
        }