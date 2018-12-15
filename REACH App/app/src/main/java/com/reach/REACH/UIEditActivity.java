package com.reach.REACH;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

public class UIEditActivity extends AppCompatActivity {
    private Button Edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //add to Database
                Intent intent = new Intent(UIEditActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });
    }
}