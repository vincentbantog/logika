package com.example.logika.gameActivities.TorF.twoPlayers.InstructionsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.gameActivities.TorF.twoPlayers.TicTacToeMain;

public class TTTInstructions1 extends AppCompatActivity {

    private Button btnBack;
    private Button btnNextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tttinstructions1);

        btnBack = findViewById(R.id.btnBack);
        btnNextPage = findViewById(R.id.btnNextPage);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTTInstructions1.this, TicTacToeMain.class);
                startActivity(intent);
                finish();
            }
        });

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TTTInstructions1.this, TTTInstructions2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}