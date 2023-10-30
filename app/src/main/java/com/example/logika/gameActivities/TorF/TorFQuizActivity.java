package com.example.logika.gameActivities.TorF;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logika.R;
import com.example.logika.gameActivities.logiQuiz.LogicGate;
import com.example.logika.gameActivities.logiQuiz.QuizActivity;

import java.util.Collections;
import java.util.List;

public class TorFQuizActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView txtScore;
    private TextView txtLives;

    private ProgressBar timerBar;

    private TextView txtQuestionCount;
    private TextView txtQuestion;

    private RadioGroup rbGroupChoices;
    private RadioButton rbChoiceTrue;
    private RadioButton rbChoiceFalse;

    private Button btnConfirm;

    private ColorStateList textColorDefaultRb;

    private List<TFQuestion> questionList;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private int questionCounter;
    private int questionCountTotal;
    private TFQuestion currentQuestion;


    private int score;
    private boolean answered;

    private int livesCount;
    private boolean isGameOver;

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fquiz);

        initializeViewElements();

        textColorDefaultRb = rbChoiceTrue.getTextColors();

        TFQuizDbHelper dbHelper = new TFQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        livesCount = 3;

        showNextQuestion();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rbChoiceTrue.isChecked() || rbChoiceFalse.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(TorFQuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void initializeViewElements(){
        txtScore = findViewById(R.id.txtScore);
        timerBar = findViewById(R.id.timerBar);
        txtLives = findViewById(R.id.txtLives);
        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtQuestion = findViewById(R.id.txtQuestion);
        rbGroupChoices = findViewById(R.id.radioGroupChoices);
        rbChoiceTrue = findViewById(R.id.radioButtonOptionTrue);
        rbChoiceFalse = findViewById(R.id.radioButtonOptionFalse);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void showNextQuestion() {
        rbChoiceTrue.setTextColor(textColorDefaultRb);
        rbChoiceFalse.setTextColor(textColorDefaultRb);
        rbGroupChoices.clearCheck();

        txtLives.setText("Lives: " + livesCount);

        if (livesCount == 0) {
            finishQuiz();
        }

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            txtQuestion.setText(currentQuestion.getQuestion());
            rbChoiceTrue.setText(currentQuestion.getOption1());
            rbChoiceFalse.setText(currentQuestion.getOption2());

            questionCounter++;
            txtQuestionCount.setText("0" + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnConfirm.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountdown();
        } else {
         finishQuiz();
        }
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateProgressBar();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                timerBar.setProgress(0);
                checkAnswer();
            }
        }.start();
    }

    private void updateProgressBar(){
        int progress = (int) (timeLeftInMillis * 100 / COUNTDOWN_IN_MILLIS);
        timerBar.setProgress(progress);
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rbSelected = findViewById(rbGroupChoices.getCheckedRadioButtonId());
        int answerNr = rbGroupChoices.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score++;
            txtScore.setText("Score: " + score);
        } else {
            livesCount--;
        }

        showSolution();
    }

    private void showSolution() {
        rbChoiceTrue.setTextColor(Color.RED);
        rbChoiceFalse.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rbChoiceTrue.setTextColor(Color.GREEN);
                break;
            case 2:
                rbChoiceFalse.setTextColor(Color.GREEN);
                break;
        }

        if (questionCounter < questionCountTotal) {
            btnConfirm.setText("Next");
        } else {
            btnConfirm.setText("Finish");
        }

    }

    private void finishQuiz(){
        finish();
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            Intent intent = new Intent(TorFQuizActivity.this, TrueOrFalse.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press back again to confirm", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}