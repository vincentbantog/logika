package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.logika.R;


import java.util.Collections;
import java.util.List;

public class BQQuizActivity extends AppCompatActivity {

    private TextView txtQuestionNumber;
    private TextView txtQuestion;

    private Button btnChoice1;
    private Button btnChoice2;
    private Button btnChoice3;
    private Button btnChoice4;


    private List<BQQuestion> questionList;

    private int questionCounter;
    private int questionCountTotal;
    private boolean answered;
    private int score;
    private BQQuestion currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqquiz);

        initializeViewElements();

        BQQuizDbHelper dbHelper = new BQQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
    }

    private void initializeViewElements() {
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnChoice1 = findViewById(R.id.btnChoice1);
        btnChoice2 = findViewById(R.id.btnChoice2);
        btnChoice3 = findViewById(R.id.btnChoice3);
        btnChoice4 = findViewById(R.id.btnChoice4);
    }

    private void showNextQuestion() {

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            txtQuestion.setText(currentQuestion.getQuestion());

            btnChoice1.setText(currentQuestion.getOption1());
            btnChoice2.setText(currentQuestion.getOption2());
            btnChoice3.setText(currentQuestion.getOption3());
            btnChoice4.setText(currentQuestion.getOption4());

            questionCounter++;
            txtQuestionNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }

    }




    private void finishQuiz() {
        Intent intent = new Intent(BQQuizActivity.this, MultipleChoice.class);
        startActivity(intent);
    }
}