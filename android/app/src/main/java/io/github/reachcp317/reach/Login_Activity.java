package io.github.reachcp317.reach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button Register;
    private Button Map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Name = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Login = findViewById(R.id.login);
        Register = findViewById(R.id.Register);
        Map =  findViewById(R.id.Map);

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
        Map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }


    private void validate(String userName, String userPassword) {
        //check the database then validate user
        if ((userName.equals("Admin")) && (userPassword.equals("1234"))) {
            finish();
            startActivity(new Intent(Login_Activity.this,UIActivity.class));
        }
    }
}
