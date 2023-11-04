package com.example.logika.gameActivities.logiQuiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class LogicGate extends AppCompatActivity {
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    private Button btnBack;
    private Button btnBeginNormal;
    private Button btnBeginHard;

    private String difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_gate);

        initializeViewElements();

        String[] difficultyLevels = Question.getAllDifficultyLevels();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogicGate.this, MainMenu.class);
                startActivity(intent);

                finish();
            }
        });

        btnBeginNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = difficultyLevels[0];


                Intent intent = new Intent(LogicGate.this, QuizActivity.class);
                intent.putExtra(EXTRA_DIFFICULTY, difficulty);
                startActivity(intent);
            }
        });

        btnBeginHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = difficultyLevels[1];


                Intent intent = new Intent(LogicGate.this, QuizActivity.class);
                intent.putExtra(EXTRA_DIFFICULTY, difficulty);
                startActivity(intent);
            }
        });
    }

    private void initializeViewElements(){
        btnBack = findViewById(R.id.btnBack);
        btnBeginNormal = findViewById(R.id.btnBeginNormal);
        btnBeginHard = findViewById(R.id.btnBeginHard);
    }









}