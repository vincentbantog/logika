package com.example.logika.gameActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.logika.R;
import com.example.logika.mainMenuFragments.HomeFragment;

public class MultipleChoice extends AppCompatActivity {

    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        configureBtnBack();


    }

    public void configureBtnBack(){
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateBackToHome();
            }
        });
    }

    public void navigateBackToHome(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        boolean isFragmentInBackStack = fragmentManager.popBackStackImmediate("homeFragmentTag", 0);

        if (!isFragmentInBackStack) {
            Fragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.mainMenuContainer, homeFragment, "homeFragmentTag")
                    .addToBackStack("homeFragmentTag")
                    .commit();
        }
    }
}