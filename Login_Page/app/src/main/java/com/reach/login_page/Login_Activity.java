package com.reach.login_page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Activity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Info;
    private int counter = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        Name = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.Login);
        Register = (Button)findViewById(R.id.Register);
        Info.setText("Number of attempts remaining: 5");

        if(Name!=null){
            finish();
            startActivity(new Intent(Login_Activity.this,UIActivity.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    validate(Name.getText().toString(),Password.getText().toString());
                }
            });
        Register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //add to Database
                Intent intent = new Intent(Login_Activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private void logout() {

    }

    private void validate(String userName, String userPassword) {
        //Check with database
        if ((userName == "Admin") && (userPassword == "1234")) {
            //next page
        }else{
            counter--;
            Info.setText("Number of attempts remaining: "+String.valueOf(counter));
           if(counter == 0){
                Login.setEnabled(false);
            }
        }
    }



}
