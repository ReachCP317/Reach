package io.github.reachcp317.reach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UIActivity extends AppCompatActivity {
    private Button Edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        Edit = findViewById(R.id.Edit);
        Edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UIActivity.this, UIEditActivity.class);
                startActivity(intent);
            }
        });
    }
}