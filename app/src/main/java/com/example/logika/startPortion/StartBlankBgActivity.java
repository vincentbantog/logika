package com.example.logika.startPortion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class StartBlankBgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_blank_bg);

        showSecondLoadingScreen();
    }

    public void showSecondLoadingScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartBlankBgActivity.this, StartingLoadingScreen.class);
                startActivity(mainIntent);
                finish();
            }
        }, 500);
    }
}