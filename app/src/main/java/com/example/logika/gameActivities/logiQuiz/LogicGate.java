package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class LogicGate extends AppCompatActivity {

    private Button btnBack;

    private Button btnBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_gate);

        configureBackButton();
        configureBeginButton();

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

    public void configureBeginButton() {
        btnBegin = findViewById(R.id.btnBegin);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LogicGate.this, QuizActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }


}