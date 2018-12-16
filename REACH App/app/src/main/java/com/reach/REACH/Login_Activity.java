package com.reach.REACH;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * @author Midusa Nadeswarathasan
 *  * nade1680@mylaurier.ca
 */

public class Login_Activity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Info;
    private int counter = 5;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login_, container, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Name = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Info = (TextView) findViewById(R.id.tvInfo);
        Login = (Button) findViewById(R.id.login);
        Register = (Button) findViewById(R.id.Register);
        Info.setText("Number of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Login_Activity.this,"Registerd", Toast.LENGTH_LONG).show();
            }
        });

    }


    private void validate(String userName, String userPassword) {
        //check the database then validate user
        if ((userName.equals("Admin")) && (userPassword.equals("password"))) {
            finish();
            startActivity(new Intent(Login_Activity.this, UIActivity.class));

        }else {
                counter--;
                Info.setText("Number of attempts remaining: " + String.valueOf(counter));
                if (counter == 0) {
                    Login.setEnabled(false);
                }
            }
        }
    }