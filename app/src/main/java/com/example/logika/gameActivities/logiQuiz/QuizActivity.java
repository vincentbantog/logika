package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.logika.R;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment1;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment2;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initializeViewElements();

        textColorDefaultRb = rb1.getTextColors();

        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestion();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();
    }

    private void initializeViewElements(){
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtQuestionNumber);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButtonOption1);
        rb2 = findViewById(R.id.radioButtonOption2);
        rb3 = findViewById(R.id.radioButtonOption3);
        buttonConfirmNext = findViewById(R.id.btnConfirmAnswer);
    }

    private void showNextQuestion() {
        currentQuestion = questionList.get(questionCounter);
        fragment = createFragmentBasedOnType(currentQuestion.getCircuitFragment());

        fragmentManager.beginTransaction()
                .replace(R.id.questionFragmentContainer, fragment.getClass(), null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

        rb1.setText(currentQuestion.getOption1());
        rb2.setText(currentQuestion.getOption2());
        rb3.setText(currentQuestion.getOption3());
    }

    public Fragment createFragmentBasedOnType(int circuitFragment){
        Fragment fragment = null;

        switch (circuitFragment) {
            case 1:
                fragment = new QuestionFragment1();
                break;
            case 2:
                fragment = new QuestionFragment2();
                break;
            default:
                break;
        }

    return fragment;

    }


}