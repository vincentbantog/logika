package com.example.logika.gameActivities.TorF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.gameActivities.TorF.singlePlayer.TrueOrFalse;
import com.example.logika.gameActivities.TorF.twoPlayers.TicTacToeMain;

public class TFHomePage extends AppCompatActivity {

    private Button btnBack;
    private Button btnSinglePlayerTF;
    private Button btnTwoPlayerTF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tfhome_page);

        configureBackButton();
        configureSinglePlayerButton();
        configureTwoPlayerButton();
    }

    private void configureBackButton(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TFHomePage.this, MainMenu.class);
                startActivity(intent);

                finish();
            }
        });
    }

    private void configureSinglePlayerButton(){
        btnSinglePlayerTF = findViewById(R.id.btnSinglePlayerTF);
        btnSinglePlayerTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TFHomePage.this, TrueOrFalse.class);
                startActivity(intent);

                finish();
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

                finish();
            }
        });
    }
}