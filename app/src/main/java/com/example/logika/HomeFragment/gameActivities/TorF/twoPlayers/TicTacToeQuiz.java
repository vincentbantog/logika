package com.example.logika.HomeFragment.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logika.HomeFragment.gameActivities.TorF.databaseClasses.TFQuestion;
import com.example.logika.HomeFragment.gameActivities.TorF.databaseClasses.TFQuizDbHelper;
import com.example.logika.R;

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
    private ImageView imageViewTrue;
    private ImageView imageViewFalse;

    private Button btnConfirm;
    private ImageView imageViewConfirm;

    private List<TFQuestion> questionList;
    private TFQuestion currentQuestion;
    private boolean answered;

    private boolean isCorrect;

    private Dialog leaveDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
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
        imageViewTrue = findViewById(R.id.TTT_imageViewTrue);
        imageViewFalse = findViewById(R.id.TTT_imageViewFalse);
        btnConfirm = findViewById(R.id.btnConfirm);
        imageViewConfirm = findViewById(R.id.imageViewConfirm);
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
        configureRadioButtons();
        currentQuestion = questionList.get(0);

        txtTopic.setText("Topic: " + currentQuestion.getTopic());

        radioButtonTrue.setEnabled(true);
        radioButtonFalse.setEnabled(true);

        txtQuestion.setText(currentQuestion.getQuestion());

        answered = false;
        imageViewConfirm.setImageResource(R.drawable.tf_010);

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();

    }

    private void configureRadioButtons(){
        imageViewTrue.setImageResource(R.drawable.tc_004);
        imageViewFalse.setImageResource(R.drawable.tc_005);

        radioButtonTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageViewTrue.setImageResource(R.drawable.tc_024);
                } else {
                    imageViewTrue.setImageResource(R.drawable.tc_004);
                }
            }
        });

        radioButtonFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    imageViewFalse.setImageResource(R.drawable.tc_025);
                } else {
                    imageViewFalse.setImageResource(R.drawable.tc_005);
                }
            }
        });
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
            txtQuestion.setText("You gain this turn!\n\nClick Next!");
            isCorrect = true;
        }  else {
            txtQuestion.setText("You lose this turn\n\nClick Next!");
            isCorrect = false;
        }

        radioButtonTrue.setEnabled(false);
        radioButtonFalse.setEnabled(false);


        imageViewConfirm.setImageResource(R.drawable.tc_022);
    }

    private void showLeaveGameDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lq_022);

        int newWidth = 360;
        int newHeight = 360;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        leaveDialog = new Dialog(this);
        leaveDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        leaveDialog.setContentView(R.layout.leave_game_dialog_logiquiz);


        Button btnResume = leaveDialog.findViewById(R.id.btnContinue);
        Button btnExit = leaveDialog.findViewById(R.id.btnExit);

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveDialog.dismiss();
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

        leaveDialog.show();
    }

    @Override
    public void onBackPressed() {
        showLeaveGameDialog();
    }

}