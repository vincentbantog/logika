package com.example.logika.HomeFragment.gameActivities.logiQuiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.logika.HomeFragment.gameActivities.logiQuiz.InstructionsActivities.LogiquizInstructions1;
import com.example.logika.MainMenu;
import com.example.logika.R;

public class LogicGate extends AppCompatActivity {
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    private Button btnBack;
    private Button btnBeginNormal;
    private Button btnBeginHard;
    private Button btnBeginInstructions;

    private String difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
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
                intent.putExtra(LogicGate.EXTRA_DIFFICULTY, difficulty);
                startActivity(intent);

                finish();
            }
        });

        btnBeginHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = difficultyLevels[1];


                Intent intent = new Intent(LogicGate.this, QuizActivity.class);
                intent.putExtra(EXTRA_DIFFICULTY, difficulty);
                startActivity(intent);

                finish();
            }
        });

        btnBeginInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogicGate.this, LogiquizInstructions1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeViewElements(){
        btnBack = findViewById(R.id.btnBack);
        btnBeginNormal = findViewById(R.id.btnBeginNormal);
        btnBeginHard = findViewById(R.id.btnBeginHard);
        btnBeginInstructions = findViewById(R.id.btnBeginInstructions);
    }









}