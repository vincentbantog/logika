package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logika.R;


import java.util.Collections;
import java.util.List;

public class BQQuizActivity extends AppCompatActivity {

    private TextView txtQuestionNumber;
    private TextView txtQuestion;
    private TextView txtScore;


    private ColorStateList textColorDefaultRb;
    private RadioGroup rbGroup;
    private RadioButton radioBtnChoice1;
    private RadioButton radioBtnChoice2;
    private RadioButton radioBtnChoice3;
    private RadioButton radioBtnChoice4;
    private Button btnConfirm;

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

        textColorDefaultRb = radioBtnChoice1.getTextColors();

        BQQuizDbHelper dbHelper = new BQQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (radioBtnChoice1.isChecked() || radioBtnChoice2.isChecked() || radioBtnChoice3.isChecked() || radioBtnChoice4.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(BQQuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void initializeViewElements() {
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtScore = findViewById(R.id.txtBQScore);
        rbGroup = findViewById(R.id.radioGroupChoices);
        radioBtnChoice1 = findViewById(R.id.radioBtnOption1);
        radioBtnChoice2 = findViewById(R.id.radioBtnOption2);
        radioBtnChoice3 = findViewById(R.id.radioBtnOption3);
        radioBtnChoice4 = findViewById(R.id.radioBtnOption4);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void showNextQuestion() {
        radioBtnChoice1.setTextColor(textColorDefaultRb);
        radioBtnChoice1.setChecked(false);
        radioBtnChoice2.setTextColor(textColorDefaultRb);
        radioBtnChoice2.setChecked(false);
        radioBtnChoice3.setTextColor(textColorDefaultRb);
        radioBtnChoice3.setChecked(false);
        radioBtnChoice4.setTextColor(textColorDefaultRb);
        radioBtnChoice4.setChecked(false);


        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            txtQuestion.setText(currentQuestion.getQuestion());
            radioBtnChoice1.setText(currentQuestion.getOption1());
            radioBtnChoice2.setText(currentQuestion.getOption2());
            radioBtnChoice3.setText(currentQuestion.getOption3());
            radioBtnChoice4.setText(currentQuestion.getOption4());


            questionCounter++;
            txtQuestionNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnConfirm.setText("Confirm");

        } else {
            finishQuiz();
        }

    }

    private void checkAnswer() {
        answered = true;

        getCheckedRadioButton();

        if (getCheckedRadioButton() == currentQuestion.getAnswerNr()) {
            score++;
            txtScore.setText("Score: " + score);
        }

        showSolution();
    }

    private int getCheckedRadioButton() {
        int selectedOption = -1;

        if (radioBtnChoice1.isChecked()){
            selectedOption = 1;
        } else if (radioBtnChoice2.isChecked()){
            selectedOption = 2;
        } else if (radioBtnChoice3.isChecked()){
            selectedOption = 3;
        } else if (radioBtnChoice4.isChecked()){
            selectedOption = 4;
        }

        return selectedOption;
    }

    private void showSolution() {
        radioBtnChoice1.setTextColor(Color.RED);
        radioBtnChoice2.setTextColor(Color.RED);
        radioBtnChoice3.setTextColor(Color.RED);
        radioBtnChoice4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                radioBtnChoice1.setTextColor(Color.GREEN);
                break;
            case 2:
                radioBtnChoice2.setTextColor(Color.GREEN);
                break;
            case 3:
                radioBtnChoice3.setTextColor(Color.GREEN);
                break;
            case 4:
                radioBtnChoice4.setTextColor(Color.GREEN);
                break;
        }

        if (questionCounter < questionCountTotal) {
            btnConfirm.setText("Next");
        } else {
            btnConfirm.setText("Finish");
        }
    }
    private void finishQuiz() {
        Intent intent = new Intent(BQQuizActivity.this, MultipleChoice.class);
        startActivity(intent);
    }
}