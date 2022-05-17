package com.example.zealand_electric;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button CreateChecklistButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("");
        //ideal function
        //SwitchActivityTo(ButtonID,String from,String to);
        CreateChecklistButton = findViewById(R.id.CreateChecklistButton);
        CreateChecklistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NewCustomerFragment.class ));
            }
        });


    }


}
