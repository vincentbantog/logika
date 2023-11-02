package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;
import com.example.logika.gameActivities.basiQuiz.BQEndActivity;
import com.example.logika.gameActivities.basiQuiz.BQQuizActivity;
import com.example.logika.gameActivities.basiQuiz.MultipleChoice;

public class TorFEndActivity extends AppCompatActivity {

    private TextView txtScore;
    private Button btnRetry;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fend);

        txtScore = findViewById(R.id.txtScore);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        int score = intent.getIntExtra(BQQuizActivity.EXTRA_SCORE, 0);
        txtScore.setText("Score: " + score + "/10");

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorFEndActivity.this, TorFQuizActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorFEndActivity.this, TrueOrFalse.class);
                startActivity(intent);
            }
        });
    }
}


