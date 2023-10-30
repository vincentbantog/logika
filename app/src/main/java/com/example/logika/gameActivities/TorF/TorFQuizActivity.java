package com.example.logika.gameActivities.TorF;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.logika.R;

import java.util.List;

public class TorFQuizActivity extends AppCompatActivity {


    private List<TFQuestion> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fquiz);

        initializeViewElements();

        TFQuizDbHelper dbHelper = new TFQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
    }

    public void initializeViewElements(){

    }
}