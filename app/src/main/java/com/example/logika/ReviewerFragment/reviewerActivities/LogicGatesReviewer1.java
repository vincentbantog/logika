package com.example.logika.ReviewerFragment.reviewerActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class LogicGatesReviewer1 extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logic_gates_reviewer1);

        initializeViewElements();
        configureBackButton();
    }

    private void initializeViewElements(){
        btnBack = findViewById(R.id.btnBack);
    }

    private void configureBackButton(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogicGatesReviewer1.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });
    }
}