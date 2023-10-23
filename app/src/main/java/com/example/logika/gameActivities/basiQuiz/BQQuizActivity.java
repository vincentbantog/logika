package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.logika.R;


import java.util.List;

public class BQQuizActivity extends AppCompatActivity {

    private List<BQQuestion> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqquiz);

        BQQuizDbHelper dbHelper = new BQQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
    }
}