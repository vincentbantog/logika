package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.logika.startPortion.mainMenuFragments.HomeFragment;
import com.example.logika.startPortion.mainMenuFragments.SettingsFragment;
import com.example.logika.startPortion.mainMenuFragments.StatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainMenu extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    StatsFragment statsFragment = new StatsFragment();
    SettingsFragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        showBottomNavigationDrawer();
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
}