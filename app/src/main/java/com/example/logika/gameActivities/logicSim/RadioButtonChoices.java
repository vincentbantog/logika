package com.example.logika.gameActivities.logicSim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;

public class RadioButtonChoices extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_choices);

        configureBackButton();
    }

    public void configureBackButton () {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(RadioButtonChoices.this, LogicGate.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}