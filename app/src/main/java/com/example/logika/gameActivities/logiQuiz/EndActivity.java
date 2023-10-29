package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;

public class EndActivity extends AppCompatActivity {
    private TextView txtScore;
    private Button btnTryAgain;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        txtScore = findViewById(R.id.txtScore);
        btnTryAgain = findViewById(R.id.btnTryAgain);
        btnHome = findViewById(R.id.btnHome);


        Intent intent = getIntent();
        int score = intent.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
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
        startActivity(intent);
    }

    private void home(){
        Intent intent = new Intent(EndActivity.this, LogicGate.class);
        startActivity(intent);
    }

}