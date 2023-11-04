package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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
import com.example.logika.gameActivities.TorF.TFHomePage;
import com.example.logika.gameActivities.TorF.databaseClasses.TFQuestion;
import com.example.logika.gameActivities.TorF.databaseClasses.TFQuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class TorFQuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_EASY_SCORE = "extraEasyScore";
    public static final String EXTRA_MEDIUM_SCORE = "extraMediumScore";
    public static final String EXTRA_HARD_SCORE = "extraHardScore";
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView txtScore;
    private TextView txtLives;

    private Button btnBack;

    private ImageView imageViewLives1;
    private ImageView imageViewLives2;
    private ImageView imageViewLives3;

    private ProgressBar timerBar;
    private TextView txtTimer;

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
    private int easyScore;
    private int mediumScore;
    private int hardScore;
    private boolean answered;

    private int livesCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tor_fquiz);

        initializeViewElements();
        configureBackButton();

        textColorDefaultRb = rbChoiceTrue.getTextColors();

        TFQuizDbHelper dbHelper = new TFQuizDbHelper(this);
        questionList = new ArrayList<>();

        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Easy", 3));
        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Medium", 4));
        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Hard", 3));


        questionCountTotal = questionList.size();


        livesCount = 3;
        imageViewLives1.setImageResource(R.drawable.simulation_output_display_on);
        imageViewLives2.setImageResource(R.drawable.simulation_output_display_on);
        imageViewLives3.setImageResource(R.drawable.simulation_output_display_on);

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
        btnBack = findViewById(R.id.btnBack);
        imageViewLives1 = findViewById(R.id.imageViewLives1);
        imageViewLives2 = findViewById(R.id.imageViewLives2);
        imageViewLives3 = findViewById(R.id.imageViewLives3);
        timerBar = findViewById(R.id.timerBar);
        txtTimer = findViewById(R.id.txtTimer);
        txtLives = findViewById(R.id.txtLives);
        txtQuestionCount = findViewById(R.id.txtQuestionCount);
        txtQuestion = findViewById(R.id.txtQuestion);
        rbGroupChoices = findViewById(R.id.radioGroupChoices);
        rbChoiceTrue = findViewById(R.id.radioButtonOptionTrue);
        rbChoiceFalse = findViewById(R.id.radioButtonOptionFalse);
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

    private void showNextQuestion() {
        rbChoiceTrue.setTextColor(textColorDefaultRb);
        rbChoiceFalse.setTextColor(textColorDefaultRb);
        rbGroupChoices.clearCheck();

        txtLives.setText("Lives: " + livesCount);

        rbChoiceTrue.setEnabled(true);
        rbChoiceFalse.setEnabled(true);

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
        if (currentQuestion.getIsBonusQuestion() == 1){
            txtQuestion.setText("BONUS QUESTION: \n" + currentQuestion.getQuestion());
            rbChoiceTrue.setText(currentQuestion.getOption1());
            rbChoiceFalse.setText(currentQuestion.getOption2());
        } else {
            txtQuestion.setText(currentQuestion.getQuestion());
            rbChoiceTrue.setText(currentQuestion.getOption1());
            rbChoiceFalse.setText(currentQuestion.getOption2());
        }

            questionCounter++;
            txtQuestionCount.setText(questionCounter + "/" + questionCountTotal);
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
                updateCountDownText();
                updateProgressBar();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                timerBar.setProgress(0);
                checkAnswer();
            }
        }.start();
    }

    private void updateProgressBar(){
        int progress = (int) (timeLeftInMillis * 100 / COUNTDOWN_IN_MILLIS);
        timerBar.setProgress(progress);
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtTimer.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            txtTimer.setTextColor(Color.RED);
        } else {
            txtTimer.setTextColor(Color.BLACK);
        }
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroupChoices.getCheckedRadioButtonId());
        int answerNr = rbGroupChoices.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score++;
            txtScore.setText("Score: " + score);
            showCorrectAnswerDialog();

            if (currentQuestion.getIsBonusQuestion() == 1){
                if (livesCount < 3) {
                    livesCount++;
                }
            }

            String difficulty = currentQuestion.getDifficulty();
            if ("Easy".equals(difficulty)){
                easyScore++;
            } else if ("Medium".equals(difficulty)){
                mediumScore++;
            } else if ("Hard".equals(difficulty)){
                hardScore++;
            }

        } else {
            showWrongAnswerDialog();
            if (currentQuestion.getIsBonusQuestion() != 1){
                livesCount--;
            }
        }

        showSolution();

        if (livesCount == 0) {
            finishQuiz();
        } else if (livesCount == 3){
            imageViewLives3.setImageResource(R.drawable.simulation_output_display_on);
        } else if (livesCount == 2){
            imageViewLives3.setImageResource(R.drawable.simulation_output_display_off);
            imageViewLives2.setImageResource(R.drawable.simulation_output_display_on);
        } else if (livesCount == 1){
            imageViewLives2.setImageResource(R.drawable.simulation_output_display_off);
        }
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

        rbChoiceTrue.setEnabled(false);
        rbChoiceFalse.setEnabled(false);

        if (questionCounter < questionCountTotal) {
            btnConfirm.setText("Next");
        } else {
            btnConfirm.setText("Finish");
        }

    }



    private void finishQuiz(){
        Intent intent = new Intent(TorFQuizActivity.this, TorFEndActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_EASY_SCORE, easyScore);
        intent.putExtra(EXTRA_MEDIUM_SCORE, mediumScore);
        intent.putExtra(EXTRA_HARD_SCORE, hardScore);

        startActivity(intent);

        finish();
    }

    private void showCorrectAnswerDialog(){
        Dialog dialog = new Dialog(this);

        if (currentQuestion.getIsBonusQuestion() == 1){
            dialog.setContentView(R.layout.correct_answer_bonus_dialog_torf);
        } else {
            dialog.setContentView(R.layout.correct_answer_dialog_basiquiz);
        }

        TextView messageText = dialog.findViewById(R.id.txtMessage);
        Button btnCloseDialog = dialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showWrongAnswerDialog(){
        Dialog dialog = new Dialog(this);
        if (currentQuestion.getIsBonusQuestion() == 1) {
            dialog.setContentView(R.layout.wrong_answer_dialog_basiquiz);
        } else {
            dialog.setContentView(R.layout.wrong_answer_dialog_torf);
        }
        Button btnCloseDialog = dialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showLeaveGameDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.leave_game_dialog_logiquiz);

        TextView messageText = dialog.findViewById(R.id.txtMessage);
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
                Intent intent = new Intent(TorFQuizActivity.this, TrueOrFalse.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }
}