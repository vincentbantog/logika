package com.example.logika.startPortion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.logika.R;

public class StartingLoadingScreen extends AppCompatActivity {


    private VideoView videoView;
    private ImageView backgroundImage;
    private RelativeLayout relLayoutMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_starting_loading_screen);

        videoView = findViewById(R.id.videoView);
        backgroundImage = findViewById(R.id.background_image);
        relLayoutMain = findViewById(R.id.mainRelLayout);
        relLayoutMain.setBackgroundColor(ContextCompat.getColor(this, android.R.color.black));

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.l_load1;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startFadeInAnimation(backgroundImage);
//                Intent intent = new Intent(StartingLoadingScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        relLayoutMain.setBackgroundResource(R.drawable.l_load4); // Change the background here after a delay
                    }
                }, 300);
            }
        });





    }

    private void startFadeInAnimation(ImageView imageView) {
        imageView.setVisibility(View.VISIBLE);
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(2000); // Set your desired duration here
        imageView.startAnimation(fadeIn);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(StartingLoadingScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}