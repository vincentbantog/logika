package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SecondLoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_loading_screen);

        showSecondLoadingScreen();
    }

    public void showSecondLoadingScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SecondLoadingScreen.this, MainMenu.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }
}