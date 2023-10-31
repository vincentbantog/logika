package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;
import com.example.logika.gameActivities.TorF.TFHomePage;

public class TrueOrFalse extends AppCompatActivity {

    private Button btnConfirm;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_or_false);

        configureBackButton();
        configureStartButton();
    }

    public void configureBackButton(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(TrueOrFalse.this, TFHomePage.class);
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
            }
        });
    }

}