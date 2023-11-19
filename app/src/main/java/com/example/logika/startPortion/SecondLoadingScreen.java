package com.example.logika.startPortion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.logika.MainMenu;
import com.example.logika.R;

public class SecondLoadingScreen extends AppCompatActivity {

    private ImageView gifImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_loading_screen);

    gifImageView = findViewById(R.id.gifImageView);

    adjustImageViewSize(gifImageView);


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

    private void adjustImageViewSize(ImageView imageView) {
        imageView.post(new Runnable() {
            @Override
            public void run() {
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                int screenHeight = getResources().getDisplayMetrics().heightPixels;

                int imageWidth = imageView.getDrawable().getIntrinsicWidth();
                int imageHeight = imageView.getDrawable().getIntrinsicHeight();

                float widthRatio = (float) screenWidth / imageWidth;
                float heightRatio = (float) screenHeight / imageHeight;

                float scaleFactor = Math.min(widthRatio, heightRatio);

                int finalWidth = (int) (imageWidth * scaleFactor);
                int finalHeight = (int) (imageHeight * scaleFactor);

                imageView.getLayoutParams().width = finalWidth;
                imageView.getLayoutParams().height = finalHeight;
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.requestLayout();
            }
        });
    }
}