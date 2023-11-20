package com.example.logika.HomeFragment.gameActivities.TorF.singlePlayer.InstructionsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.HomeFragment.gameActivities.TorF.singlePlayer.TrueOrFalse;
import com.example.logika.R;

public class TorFInstructions1 extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_finstructions1);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorFInstructions1.this, TrueOrFalse.class);
                startActivity(intent);
                finish();
            }
        });
    }
}