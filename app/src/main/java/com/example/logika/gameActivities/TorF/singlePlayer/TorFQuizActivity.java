package com.example.logika.gameActivities.TorF.singlePlayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.example.logika.gameActivities.TorF.singlePlayer.imageQuestionFragments.torf_image_question_fragment_1;
import com.example.logika.gameActivities.TorF.singlePlayer.imageQuestionFragments.torf_image_question_fragment_2;
import com.example.logika.gameActivities.TorF.singlePlayer.imageQuestionFragments.torf_image_question_fragment_3;
import com.example.logika.gameActivities.TorF.singlePlayer.imageQuestionFragments.torf_image_question_fragment_4;
import com.example.logika.gameActivities.TorF.singlePlayer.imageQuestionFragments.torf_image_question_fragment_5;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.PlaceHolderFragment;

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
    private ImageView imageViewTrue;
    private ImageView imageViewFalse;

    private Button btnConfirm;
    private ImageView imageViewConfirm;

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

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment questionFragment;
    private Fragment placeholderFragment;
    int imageQuestionIdentifier;

    private Dialog correctAnswerDialog;
    private Dialog wrongAnswerDialog;
    private Dialog leaveGameDialog;
    private Dialog correctAnswerBonusDialog;
    private Dialog wrongAnswerBonusDialog;
    private Dialog gameOverDialog;





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
        imageViewLives1.setImageResource(R.drawable.tf_006);
        imageViewLives2.setImageResource(R.drawable.tf_006);
        imageViewLives3.setImageResource(R.drawable.tf_006);

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
        imageViewTrue = findViewById(R.id.imageViewTrue);
        imageViewFalse = findViewById(R.id.imageViewFalse);
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

    private void showNextQuestion() {
        rbChoiceTrue.setTextColor(textColorDefaultRb);
        rbChoiceFalse.setTextColor(textColorDefaultRb);
        rbGroupChoices.clearCheck();

        txtLives.setText("Lives: " + livesCount);

        rbChoiceTrue.setEnabled(true);
        rbChoiceFalse.setEnabled(true);

        configureRadioButtonStates();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            questionFragment = createFragmentBasedOnType(currentQuestion.getImageFragmentIdentifier());
            placeholderFragment = new PlaceHolderFragment();

            imageQuestionIdentifier = currentQuestion.getImageFragmentIdentifier();
         if (imageQuestionIdentifier != 0){
                fragmentManager.beginTransaction()
                        .replace(R.id.questionFragmentContainer, questionFragment.getClass(), null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();

                txtQuestion.setText("");
            } else if (currentQuestion.getIsBonusQuestion() == 1){
             fragmentManager.beginTransaction()
                     .replace(R.id.questionFragmentContainer, placeholderFragment.getClass(), null)
                     .setReorderingAllowed(true)
                     .addToBackStack("name")
                     .commit();
             txtQuestion.setText("BONUS QUESTION: \n" + currentQuestion.getQuestion());
         }  else {
             fragmentManager.beginTransaction()
                     .replace(R.id.questionFragmentContainer, placeholderFragment.getClass(), null)
                     .setReorderingAllowed(true)
                     .addToBackStack("name")
                     .commit();
            txtQuestion.setText(currentQuestion.getQuestion());
        }

            questionCounter++;
            txtQuestionCount.setText(questionCounter + "/" + questionCountTotal);
            answered = false;
            imageViewConfirm.setImageResource(R.drawable.tf_010);

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountdown();
        } else {
         finishQuiz();
        }
    }

    public void configureRadioButtonStates(){
        imageViewTrue.setImageResource(R.drawable.tf_008);
        imageViewFalse.setImageResource(R.drawable.tf_009);

        rbChoiceTrue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewTrue.setImageResource(R.drawable.tf_027);
                    // Add logic for Option 2
                } else {
                    imageViewTrue.setImageResource(R.drawable.tf_008);
                }
            }
        });

        rbChoiceFalse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewFalse.setImageResource(R.drawable.tf_028);
                    // Add logic for Option 2
                } else {
                    imageViewFalse.setImageResource(R.drawable.tf_009);
                }
            }
        });
    }

    public Fragment createFragmentBasedOnType(int questionFragment){
        Fragment fragment = null;

        switch (questionFragment) {
            case 1:
                fragment = new torf_image_question_fragment_1();
                break;
            case 2:
                fragment = new torf_image_question_fragment_2();
                break;
            case 3:
                fragment = new torf_image_question_fragment_3();
                break;
            case 4:
                fragment = new torf_image_question_fragment_4();
                break;
            case 5:
                fragment = new torf_image_question_fragment_5();
                break;
            default:
                break;
        }
        return fragment;
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


            if (currentQuestion.getIsBonusQuestion() == 1){
                if (livesCount < 3) {
                    showBonusCorrectAnswerDialog();
                    livesCount++;
                } else {
                    showCorrectAnswerDialog();
                }
            } else {
                showCorrectAnswerDialog();
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
            if (currentQuestion.getIsBonusQuestion() != 1){
                livesCount--;
                if (livesCount == 0){
                    showGameOverDialog();
                } else {
                    showWrongAnswerDialog();
                }


            } else {
                showWrongAnswerBonusDialog();
            }
        }

        showSolution();

        if (livesCount == 0) {
            imageViewLives1.setImageResource(R.drawable.tf_013);
            imageViewConfirm.setImageResource(R.drawable.tf_016);
            questionCounter = questionCountTotal;
        } else if (livesCount == 3){
            imageViewLives3.setImageResource(R.drawable.tf_006);
        } else if (livesCount == 2){
            imageViewLives3.setImageResource(R.drawable.tf_013);
            imageViewLives2.setImageResource(R.drawable.tf_006);
        } else if (livesCount == 1){
            imageViewLives2.setImageResource(R.drawable.tf_013);
        }
    }

    private void showSolution() {
        imageViewTrue.setImageResource(R.drawable.tf_014);
        imageViewFalse.setImageResource(R.drawable.tf_026);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                imageViewTrue.setImageResource(R.drawable.tf_011);
                break;
            case 2:
                imageViewFalse.setImageResource(R.drawable.tf_015);
                break;
        }

        rbChoiceTrue.setEnabled(false);
        rbChoiceFalse.setEnabled(false);

        if (questionCounter < questionCountTotal) {
            imageViewConfirm.setImageResource(R.drawable.tf_012);
        } else {
            imageViewConfirm.setImageResource(R.drawable.tf_016);
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
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bq_011);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        correctAnswerDialog = new Dialog(this);
        correctAnswerDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        correctAnswerDialog.setContentView(R.layout.correct_answer_dialog_basiquiz);


        Button btnCloseDialog = correctAnswerDialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswerDialog.dismiss();
            }
        });

        correctAnswerDialog.show();

    }

    private void showBonusCorrectAnswerDialog(){
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tf_020);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        correctAnswerBonusDialog = new Dialog(this);
        correctAnswerBonusDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        correctAnswerBonusDialog.setContentView(R.layout.correct_answer_dialog_basiquiz);


        Button btnCloseDialog = correctAnswerBonusDialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctAnswerBonusDialog.dismiss();
            }
        });

        correctAnswerBonusDialog.show();
    }

    private void showWrongAnswerDialog(){
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tf_019);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        wrongAnswerDialog = new Dialog(this);
        wrongAnswerDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        wrongAnswerDialog.setContentView(R.layout.wrong_answer_dialog_torf);

        Button btnCloseDialog = wrongAnswerDialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongAnswerDialog.dismiss();
            }
        });

        wrongAnswerDialog.show();
    }

    private void showWrongAnswerBonusDialog(){
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bq_013);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        wrongAnswerBonusDialog = new Dialog(this);
        wrongAnswerBonusDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        wrongAnswerBonusDialog.setContentView(R.layout.wrong_answer_dialog_torf);

        Button btnCloseDialog = wrongAnswerBonusDialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongAnswerBonusDialog.dismiss();
            }
        });

        wrongAnswerBonusDialog.show();
    }

    private void showGameOverDialog(){
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tf_029);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        gameOverDialog = new Dialog(this);
        gameOverDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        gameOverDialog.setContentView(R.layout.game_over_dialog_torf);

        Button btnCloseDialog = gameOverDialog.findViewById(R.id.btnConfirm);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOverDialog.dismiss();
            }
        });

        gameOverDialog.show();
    }

    private void showLeaveGameDialog(){
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lq_022);

        int newWidth = 360;
        int newHeight = 360;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        leaveGameDialog = new Dialog(this);
        leaveGameDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        leaveGameDialog.setContentView(R.layout.leave_game_dialog_logiquiz);

        Button btnResume = leaveGameDialog.findViewById(R.id.btnContinue);
        Button btnExit = leaveGameDialog.findViewById(R.id.btnExit);

        btnResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveGameDialog.dismiss();
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

        leaveGameDialog.show();
    }

    private void dismissDialog(){
        if (correctAnswerDialog != null && correctAnswerDialog.isShowing()) {
            correctAnswerDialog.dismiss();
        }
        if (wrongAnswerDialog != null && wrongAnswerDialog.isShowing()) {
            wrongAnswerDialog.dismiss();
        }
        if (leaveGameDialog != null && leaveGameDialog.isShowing()) {
            leaveGameDialog.dismiss();
        }
        if (gameOverDialog != null && gameOverDialog.isShowing()) {
            gameOverDialog.dismiss();
        }
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
        dismissDialog();
    }
}