package com.example.logika.gameActivities.logicSim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class LogicGate extends AppCompatActivity {

    private Button btnBack;
    private Button btnLogicGateStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_gate);

        configureBackButton();
        startQuiz();

    }

    public void configureBackButton(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LogicGate.this, MainMenu.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    public void startQuiz() {
        btnLogicGateStart = findViewById(R.id.btnLogicGateStart);
        btnLogicGateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LogicGate.this, RadioButtonChoices.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}