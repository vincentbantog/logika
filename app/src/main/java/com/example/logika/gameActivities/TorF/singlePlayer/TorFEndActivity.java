package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.logika.R;


import org.w3c.dom.Text;

public class TorFEndActivity extends AppCompatActivity {

    private TextView txtScore;
    private TextView txtEasyScore;
    private TextView txtMediumScore;
    private TextView txtHardScore;
    private ProgressBar scoreBarEasy;
    private ProgressBar scoreBarMedium;
    private ProgressBar scoreBarHard;
    private Button btnRetry;
    private Button btnHome;
    private int easyScore;
    private int mediumScore;
    private int hardScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fend);

        txtScore = findViewById(R.id.txtScore);
        txtEasyScore = findViewById(R.id.txtEasyScore);
        txtMediumScore = findViewById(R.id.txtMediumScore);
        txtHardScore = findViewById(R.id.txtHardScore);
        scoreBarEasy = findViewById(R.id.progressBarEasy);
        scoreBarMedium = findViewById(R.id.progressBarMedium);
        scoreBarHard = findViewById(R.id.progressBarHard);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        int score = intent.getIntExtra(TorFQuizActivity.EXTRA_SCORE, 0);
        easyScore = intent.getIntExtra(TorFQuizActivity.EXTRA_EASY_SCORE, 0);
        mediumScore = intent.getIntExtra(TorFQuizActivity.EXTRA_MEDIUM_SCORE, 0);
        hardScore = intent.getIntExtra(TorFQuizActivity.EXTRA_HARD_SCORE, 0);
        txtScore.setText(score + "");

        updateScoreBarEasy();
        updateScoreBarMedium();
        updateScoreBarHard();

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorFEndActivity.this, TorFQuizActivity.class);
                startActivity(intent);

                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TorFEndActivity.this, TrueOrFalse.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void updateScoreBarEasy() {
        int maxProgress = scoreBarEasy.getMax();

        int progress = (int) ((easyScore * maxProgress) / 3.0);
        scoreBarEasy.setProgress(progress);
    }

    private void updateScoreBarMedium() {
        int maxProgress = scoreBarMedium.getMax();

        int progress = (int) ((mediumScore * maxProgress) / 3.0);
        scoreBarMedium.setProgress(progress);
    }

    private void updateScoreBarHard() {
        int maxProgress = scoreBarMedium.getMax();

        int progress = (int) ((hardScore * maxProgress) / 3.0);
        scoreBarHard.setProgress(progress);
    }

}


