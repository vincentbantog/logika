package com.example.logika.startPortion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.example.logika.R;

public class StartingLoadingScreen extends AppCompatActivity {


    private VideoView videoView;
    private ImageView imageView;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_loading_screen);

        videoView = findViewById(R.id.videoView);
        imageView = findViewById(R.id.imageView);
        btnStart = findViewById(R.id.btnStart);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.l_load1;

        Uri videoUri = Uri.parse(videoPath);

        videoView.setVideoURI(videoUri);

        // Adjust the video view to cover the entire screen
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false); // Set looping true/false as per your requirement

                // Adjust the size of the video view to cover the entire screen
                int videoWidth = mediaPlayer.getVideoWidth();
                int videoHeight = mediaPlayer.getVideoHeight();

                float videoProportion = (float) videoWidth / (float) videoHeight;
                int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
                int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
                float screenProportion = (float) screenWidth / (float) screenHeight;

                android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();

                if (videoProportion > screenProportion) {
                    lp.width = screenWidth;
                    lp.height = (int) ((float) screenWidth / videoProportion);
                } else {
                    lp.height = screenHeight;
                    lp.width = (int) ((float) screenHeight * videoProportion);
                }

                videoView.setLayoutParams(lp);
            }
        });



        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                showImageView();
                showButton();
                configureStartButton();
            }
        });

    }

    public void showImageView(){
        // Hide the VideoView

        // Show the final frame as ImageView
        imageView.setVisibility(ImageView.VISIBLE);

        adjustImageViewSize(imageView);


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

    private void showButton(){
        RelativeLayout wholeLayout = findViewById(R.id.relLayoutStart);


        long delayMillis = 1000; // 2 seconds delay as an example


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wholeLayout.setVisibility(View.VISIBLE);
            }
        }, delayMillis);
    }

    public void configureStartButton() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(StartingLoadingScreen.this, SecondLoadingScreen.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}