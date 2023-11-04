package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;

public class BQEndActivity extends AppCompatActivity {

    private TextView txtScore;
    private TextView txtEasyScore;
    private TextView txtMediumScore;
    private TextView txtHardScore;
    private Button btnRetry;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqend);

        txtScore = findViewById(R.id.txtScore);
        txtEasyScore = findViewById(R.id.txtEasyScore);
        txtMediumScore = findViewById(R.id.txtMediumScore);
        txtHardScore = findViewById(R.id.txtHardScore);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        int score = intent.getIntExtra(BQQuizActivity.EXTRA_SCORE, 0);
        int easyScore = intent.getIntExtra(BQQuizActivity.EXTRA_EASY_SCORE, 0);
        int mediumScore = intent.getIntExtra(BQQuizActivity.EXTRA_MEDIUM_SCORE, 0);
        int hardScore = intent.getIntExtra(BQQuizActivity.EXTRA_HARD_SCORE, 0);

        txtScore.setText("Score: " + score + "/10");
        txtEasyScore.setText(easyScore + "/3");
        txtMediumScore.setText(mediumScore + "/4");
        txtHardScore.setText(hardScore + "/3");



        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BQEndActivity.this, BQQuizActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BQEndActivity.this, MultipleChoice.class);
                startActivity(intent);

                finish();
            }
        });
    }
}