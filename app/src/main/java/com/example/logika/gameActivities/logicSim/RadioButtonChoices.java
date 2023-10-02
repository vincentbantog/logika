package com.example.logika.gameActivities.logicSim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.logika.R;

import java.util.List;

public class RadioButtonChoices extends AppCompatActivity {

    private Button btnBack;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_choices);

        configureBackButton();
        initializeViewElements();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestion();

    }

    public void configureBackButton () {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(RadioButtonChoices.this, LogicGate.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    public void initializeViewElements() {
        textViewQuestion = findViewById(R.id.txtQuestion);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtQuestionNumber);
        textViewCountDown = findViewById(R.id.txtTimer);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button_1);
        rb2 = findViewById(R.id.radio_button_2);
        rb3 = findViewById(R.id.radio_button_3);
        buttonConfirmNext = findViewById(R.id.btnConfirmNext);
    }

}