package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;


import org.w3c.dom.Text;

public class TorFEndActivity extends AppCompatActivity {

    private TextView txtScore;
    private TextView txtEasyScore;
    private TextView txtMediumScore;
    private TextView txtHardScore;
    private Button btnRetry;
    private Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fend);

        txtScore = findViewById(R.id.txtScore);
        txtEasyScore = findViewById(R.id.txtEasyScore);
        txtMediumScore = findViewById(R.id.txtMediumScore);
        txtHardScore = findViewById(R.id.txtHardScore);
        btnRetry = findViewById(R.id.btnRetry);
        btnHome = findViewById(R.id.btnHome);

        Intent intent = getIntent();
        int score = intent.getIntExtra(TorFQuizActivity.EXTRA_SCORE, 0);
        int easyScore = intent.getIntExtra(TorFQuizActivity.EXTRA_EASY_SCORE, 0);
        int mediumScore = intent.getIntExtra(TorFQuizActivity.EXTRA_MEDIUM_SCORE, 0);
        int hardScore = intent.getIntExtra(TorFQuizActivity.EXTRA_HARD_SCORE, 0);
        txtScore.setText("Score: " + score + "/10");
        txtEasyScore.setText(easyScore + "/3");
        txtMediumScore.setText(mediumScore + "/4");
        txtHardScore.setText(hardScore + "/3");

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
}


