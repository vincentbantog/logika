package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class MultipleChoice extends AppCompatActivity {

    private Button btnBack;
    private Button btnGeneralStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        configureBackButton();
        configureGeneralStartButton();


    }

    public void configureGeneralStartButton(){
        btnGeneralStart = findViewById(R.id.btnGeneralStart);
        btnGeneralStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MultipleChoice.this, BQQuizActivity.class);
                startActivity(mainIntent);
            }
        });
    }

    public void configureBackButton(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MultipleChoice.this, MainMenu.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }


}