package com.example.logika.gameActivities.TorF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.gameActivities.TorF.singlePlayer.TrueOrFalse;
import com.example.logika.gameActivities.TorF.twoPlayers.TicTacToeMain;

public class TFHomePage extends AppCompatActivity {

    private Button btnSinglePlayerTF;
    private Button btnTwoPlayerTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfhome_page);

        configureSinglePlayerButton();
        configureTwoPlayerButton();
    }

    private void configureSinglePlayerButton(){
        btnSinglePlayerTF = findViewById(R.id.btnSinglePlayerTF);
        btnSinglePlayerTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TFHomePage.this, TrueOrFalse.class);
                startActivity(intent);
            }
        });
    }

    private void configureTwoPlayerButton(){
        btnTwoPlayerTF = findViewById(R.id.btnTwoPlayerTF);
        btnTwoPlayerTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TFHomePage.this, TicTacToeMain.class);
                startActivity(intent);
            }
        });
    }
}