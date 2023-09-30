package com.example.logika;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        showBottomNavigationDrawer();
    }
    public void showBottomNavigationDrawer(){
        BottomNavigationView bottomNavigationDrawer = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.bottom_drawer_nav);
        NavigationUI.setupWithNavController(bottomNavigationDrawer, navController);


    }
}