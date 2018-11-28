package com.reach.login_page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText newUsername, newPassword, userEmail;
    private Button Register;
    private TextView userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupUIViews();
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Add  to database
                    String email = userEmail.getText().toString().trim();
                    String password = newPassword.getText().toString().trim();
                    startActivity(new Intent(SecondActivity.this,Login_Activity.class));
                }
            }
        });

    }
    private Boolean validate(){
        Boolean result = false;
        String name = newUsername.getText().toString();
        String password = newPassword.getText().toString();
        String email = userEmail.getText().toString();
        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }
    private void setupUIViews(){
        newUsername = (EditText)findViewById(R.id.Username);
        newPassword = (EditText)findViewById(R.id.Password);
        userEmail = (EditText)findViewById(R.id.Email);
        Register = (Button)findViewById(R.id.Register);
    }
}
