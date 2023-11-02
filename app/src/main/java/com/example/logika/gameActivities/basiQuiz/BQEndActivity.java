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
    private Button btnRetry;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqend);

        txtScore = findViewById(R.id.txtScore);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        int score = intent.getIntExtra(BQQuizActivity.EXTRA_SCORE, 0);
        txtScore.setText("Score: " + score + "/10");


        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BQEndActivity.this, BQQuizActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BQEndActivity.this, MultipleChoice.class);
                startActivity(intent);
            }
        });
    }
}