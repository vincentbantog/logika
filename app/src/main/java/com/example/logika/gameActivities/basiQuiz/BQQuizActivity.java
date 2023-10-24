package com.example.logika.gameActivities.basiQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
    private ImageView imageViewChoice1;
    private RadioButton radioBtnChoice2;
    private ImageView imageViewChoice2;
    private RadioButton radioBtnChoice3;
    private ImageView imageViewChoice3;
    private RadioButton radioBtnChoice4;
    private ImageView imageViewChoice4;
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
        imageViewChoice1 = findViewById(R.id.imageViewChoice1);

        radioBtnChoice2 = findViewById(R.id.radioBtnOption2);
        imageViewChoice2 = findViewById(R.id.imageViewChoice2);

        radioBtnChoice3 = findViewById(R.id.radioBtnOption3);
        imageViewChoice3 = findViewById(R.id.imageViewChoice3);

        radioBtnChoice4 = findViewById(R.id.radioBtnOption4);
        imageViewChoice4 = findViewById(R.id.imageViewChoice4);

        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void showNextQuestion() {
        radioBtnChoice1.setTextColor(textColorDefaultRb);
        radioBtnChoice2.setTextColor(textColorDefaultRb);
        radioBtnChoice3.setTextColor(textColorDefaultRb);
        radioBtnChoice4.setTextColor(textColorDefaultRb);

        configureRadioButtonState();

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

    public void configureRadioButtonState(){
        radioBtnChoice1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice1.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice1.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice2.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice2.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice3.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice3.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice4.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice4.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });
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