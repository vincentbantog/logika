package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.logika.R;

import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private List<Question> questionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestion();
    }
}