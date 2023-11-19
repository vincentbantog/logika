package com.example.logika.startPortion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.logika.R;

public class StartingLoadingScreen extends AppCompatActivity {


    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_loading_screen);

        videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.l_load1;

        Uri videoUri = Uri.parse(videoPath);

        videoView.setVideoURI(videoUri);

        // Adjust the video view to cover the entire screen
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(false); // Set looping true/false as per your requirement
                mediaPlayer.setVolume(0, 0); // Mute video (optional)

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
                    lp.width = (int) (videoProportion * (float) screenHeight);
                    lp.height = screenHeight;
                }

                videoView.setLayoutParams(lp);
            }
        });

        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(StartingLoadingScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



    public void showStartingLoadingScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(StartingLoadingScreen.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }
}