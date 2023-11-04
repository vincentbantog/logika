package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;

public class EndActivity extends AppCompatActivity {
    public static final String EXTRA_DIFFICULTY = "difficulty";

    private TextView txtScore;
    private Button btnTryAgain;
    private Button btnHome;

    private String difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        txtScore = findViewById(R.id.txtScore);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnHome = findViewById(R.id.btnHome);


        Intent intent = getIntent();
        int score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
        difficulty = intent.getStringExtra(QuizActivity.EXTRA_DIFFICULTY);
        txtScore.setText("Score: " + score);

        btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryAgain();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
    }

    private void tryAgain(){
        Intent intent = new Intent(EndActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivity(intent);

        finish();
    }

    private void home(){
        Intent intent = new Intent(EndActivity.this, LogicGate.class);
        startActivity(intent);

        finish();
    }

}