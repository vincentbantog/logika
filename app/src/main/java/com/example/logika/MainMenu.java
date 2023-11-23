package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.logika.startPortion.mainMenuFragments.HomeFragment;
import com.example.logika.startPortion.mainMenuFragments.SettingsFragment;
import com.example.logika.startPortion.mainMenuFragments.StatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainMenu extends AppCompatActivity implements SettingsFragment.OnExitListener {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    StatsFragment statsFragment = new StatsFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        showBottomNavigationDrawer();

        captureIdentifier();



    }

    public void showBottomNavigationDrawer() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuContainer,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuContainer, homeFragment).commit();
                    return true;
                } else if (itemId == R.id.stats) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuContainer, statsFragment).commit();
                    return true;
                } else if (itemId == R.id.settings) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainMenuContainer, settingsFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

    private void captureIdentifier(){
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("RETURN_TO_FRAGMENT")) {
            String returnToFragment = intent.getStringExtra("RETURN_TO_FRAGMENT");
            if ("SecondFragment".equals(returnToFragment)) {
                // Navigate to the second fragment within your BottomNavigationView
                // Use FragmentTransaction or navigation components to navigate
                fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                StatsFragment secondFragment = new StatsFragment();
                transaction.replace(R.id.mainMenuContainer, secondFragment); // Replace with your fragment container ID
                transaction.commit();

                bottomNavigationView.setSelectedItemId(R.id.stats);
            }
        }
    }

    @Override
    public void onExitRequested() {
        // Close the application by finishing the activity stack
        finishAffinity();
    }

}