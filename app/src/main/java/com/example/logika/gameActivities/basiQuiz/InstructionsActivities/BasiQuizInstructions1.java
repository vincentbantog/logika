package com.example.logika.gameActivities.basiQuiz.InstructionsActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.gameActivities.basiQuiz.MultipleChoice;

public class BasiQuizInstructions1 extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basi_quiz_instructions1);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasiQuizInstructions1.this, MultipleChoice.class);
                startActivity(intent);
                finish();
            }
        });
    }
}