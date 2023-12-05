package com.example.logika.HomeFragment.gameActivities.basiQuiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.logika.R;

public class BQEndActivity extends AppCompatActivity {

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
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_bqend);

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
        int score = intent.getIntExtra(BQQuizActivity.EXTRA_SCORE, 0);
        easyScore = intent.getIntExtra(BQQuizActivity.EXTRA_EASY_SCORE, 0);
        mediumScore = intent.getIntExtra(BQQuizActivity.EXTRA_MEDIUM_SCORE, 0);
        hardScore = intent.getIntExtra(BQQuizActivity.EXTRA_HARD_SCORE, 0);

        txtScore.setText(score + "");

        updateScoreBarEasy();
        updateScoreBarMedium();
        updateScoreBarHard();


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