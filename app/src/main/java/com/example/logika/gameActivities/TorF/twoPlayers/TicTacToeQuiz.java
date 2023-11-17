package com.example.logika.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicTacToeQuiz extends AppCompatActivity {
    public static final String KEY_CHECKER = "extraChecker";
    private static final long COUNTDOWN_IN_MILLIS = 20000;

    private Button btnBack;

    private TextView txtTopic;

    private ProgressBar timerBar;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private TextView txtQuestion;
    private RadioGroup radioGroupChoices;
    private RadioButton radioButtonTrue;
    private RadioButton radioButtonFalse;
    private Button btnConfirm;

    private List<TFQuestion> questionList;
    private TFQuestion currentQuestion;
    private boolean answered;

    private boolean isCorrect;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_quiz);

        initializeViewElements();
        configureBackButton();

        TFQuizDbHelper dbHelper = new TFQuizDbHelper(this);
        questionList = dbHelper.getQuestionsWithTopic();

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
        btnBack = findViewById(R.id.btnBack);
        txtTopic = findViewById(R.id.txtTopic);
        timerBar = findViewById(R.id.timerBar);
        txtQuestion = findViewById(R.id.txtQuestion);
        radioGroupChoices = findViewById(R.id.TTT_radioGroupChoices);
        radioButtonTrue = findViewById(R.id.TTT_radioButtonTrue);
        radioButtonFalse = findViewById(R.id.TTT_radioButtonFalse);
        btnConfirm = findViewById(R.id.btnConfirm);
    }

    private void configureBackButton(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLeaveGameDialog();
            }
        });
    }

    private void showQuestion(){
        currentQuestion = questionList.get(0);

        txtTopic.setText("Topic: " + currentQuestion.getTopic());

        radioButtonTrue.setEnabled(true);
        radioButtonFalse.setEnabled(true);

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

        radioButtonTrue.setEnabled(false);
        radioButtonFalse.setEnabled(false);

        btnConfirm.setText("Next");
    }


    private void showLeaveGameDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_main);

        TextView messageText = dialog.findViewById(R.id.txtTitle);
        Button btnResume = dialog.findViewById(R.id.btnContinue);
        Button btnExit = dialog.findViewById(R.id.btnExit);

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeQuiz.this, TicTacToeMain.class);
                startActivity(intent);

                finish();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        showLeaveGameDialog();
    }

}