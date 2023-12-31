package com.example.logika.HomeFragment.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.logika.HomeFragment.gameActivities.TorF.twoPlayers.InstructionsActivities.TTTInstructions1;
import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.HomeFragment.gameActivities.TorF.TFHomePage;

public class TicTacToeMain extends AppCompatActivity {

    private Button btnBegin;
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
        setContentView(R.layout.activity_tic_tac_toe_main);

        btnBegin = findViewById(R.id.btnBegin);
        btnBack = findViewById(R.id.btnBack);
        btnBeginInstructions = findViewById(R.id.btnBeginInstructions);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeMain.this, TicTacToeGame.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeMain.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        btnBeginInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeMain.this, TTTInstructions1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}