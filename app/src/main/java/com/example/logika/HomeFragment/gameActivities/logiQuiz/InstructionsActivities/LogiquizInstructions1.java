package com.example.logika.HomeFragment.gameActivities.logiQuiz.InstructionsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.LogicGate;

public class LogiquizInstructions1 extends AppCompatActivity {

    private Button btnBack;
    private Button btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logiquiz_instructions1);

        btnBack = findViewById(R.id.btnBack);
        btnNextPage = findViewById(R.id.btnNextPage);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogiquizInstructions1.this, LogicGate.class);
                startActivity(intent);
                finish();
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogiquizInstructions1.this, LogiquizInstructions2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}