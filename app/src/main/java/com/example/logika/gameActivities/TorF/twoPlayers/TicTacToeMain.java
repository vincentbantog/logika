package com.example.logika.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.gameActivities.TorF.TFHomePage;

public class TicTacToeMain extends AppCompatActivity {

    private Button btnBegin;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_main);

        btnBegin = findViewById(R.id.btnBegin);
        btnBack = findViewById(R.id.btnBack);
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
                Intent intent = new Intent(TicTacToeMain.this, TFHomePage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}