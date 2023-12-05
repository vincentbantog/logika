package com.example.logika.HomeFragment.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.HomeFragment.gameActivities.TorF.TFHomePage;
import com.example.logika.HomeFragment.gameActivities.TorF.singlePlayer.InstructionsActivities.TorFInstructions1;

public class TrueOrFalse extends AppCompatActivity {

    private Button btnConfirm;
    private Button btnBack;
    private Button btnBeginInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_true_or_false);

        configureBackButton();
        configureStartButton();
        configureInstructionsButton();
    }

    public void configureBackButton(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(TrueOrFalse.this, MainMenu.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    public void configureStartButton(){
        btnConfirm = findViewById(R.id.btnBegin);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrueOrFalse.this, TorFQuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void configureInstructionsButton(){
        btnBeginInstructions = findViewById(R.id.btnBeginInstructions);
        btnBeginInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrueOrFalse.this, TorFInstructions1.class);
                startActivity(intent);
                finish();
            }
        });
    }

}