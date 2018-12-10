package com.reach.REACH;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Info;
    private int counter = 5;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Name = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.login);
        Register = (Button)findViewById(R.id.Register);
        Info.setText("Number of attempts remaining: 5");
        Logout = (Button) findViewById(R.id.Logout);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    validate(Name.getText().toString(),Password.getText().toString());
                }
            });
        Register.setOnClickListener(new View.OnClickListener(){
            //create user class with backend
            //send to database
            @Override
            public void onClick(View v){
                //add to Database

            }
        });
        Logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                startActivity(new Intent(Login_Activity.this,Login_Activity.class));
                Toast.makeText(Login_Activity.this, "Logged out", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void validate(String userName, String userPassword) {
        //check the database then validate user
        if ((userName == "Admin") && (userPassword == "1234")) {
            finish();
            startActivity(new Intent(Login_Activity.this,UIActivity.class));
        }else{
            counter--;
            Info.setText("Number of attempts remaining: "+String.valueOf(counter));
           if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }
}

