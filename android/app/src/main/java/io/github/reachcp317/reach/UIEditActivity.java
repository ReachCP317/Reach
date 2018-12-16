package io.github.reachcp317.reach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UIEditActivity extends AppCompatActivity {
    private Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiedit);
        Save = findViewById(R.id.Save);
        Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //add to Database
                Intent intent = new Intent(UIEditActivity.this, UIActivity.class);
                startActivity(intent);
            }
        });
    }
}