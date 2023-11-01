package com.example.logika.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logika.R;
import com.example.logika.gameActivities.TorF.databaseClasses.TFQuestion;
import com.example.logika.gameActivities.TorF.databaseClasses.TFQuizDbHelper;

import java.util.Collections;
import java.util.List;

public class TicTacToeQuiz extends AppCompatActivity {
    public static final String KEY_CHECKER = "extraChecker";
    private static final long COUNTDOWN_IN_MILLIS = 20000;


    private ProgressBar timerBar;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private TextView txtQuestion;
    private RadioGroup radioGroupChoices;
    private RadioButton radioButtonTrue;
    private RadioButton radioButtonFalse;
    private Button btnConfirm;

    private List<TFQuestion> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private TFQuestion currentQuestion;
    private boolean answered;

    private boolean isCorrect;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_quiz);

        initializeViewElements();

        TFQuizDbHelper dbHelper = new TFQuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showQuestion();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (radioButtonTrue.isChecked() || radioButtonFalse.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(TicTacToeQuiz.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(KEY_CHECKER, isCorrect);

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }

    private void initializeViewElements(){
        timerBar = findViewById(R.id.timerBar);
        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroupChoices = findViewById(R.id.TTT_radioGroupChoices);
        radioButtonTrue = findViewById(R.id.TTT_radioButtonTrue);
        radioButtonFalse = findViewById(R.id.TTT_radioButtonFalse);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void showQuestion(){
        currentQuestion = questionList.get(0);

        txtQuestion.setText(currentQuestion.getQuestion());
        radioButtonTrue.setText(currentQuestion.getOption1());
        radioButtonFalse.setText(currentQuestion.getOption2());

        answered = false;
        btnConfirm.setText("Confirm");

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

    }

    private void startCountDown() {
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

        RadioButton rbSelected = findViewById(radioGroupChoices.getCheckedRadioButtonId());
        int answerNr = radioGroupChoices.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            txtQuestion.setText("You got the answer correct!");
            isCorrect = true;
        }  else {
            txtQuestion.setText("You got the answer wrong");
            isCorrect = false;
        }

        btnConfirm.setText("Next");
    }


}