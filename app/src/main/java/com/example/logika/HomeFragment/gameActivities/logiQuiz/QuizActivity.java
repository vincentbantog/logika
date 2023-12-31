package com.example.logika.HomeFragment.gameActivities.logiQuiz;

import androidx.appcompat.app.ActionBar;
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

import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment1;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment10;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment2;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment3;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment4;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment5;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment6;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment7;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment8;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.QuestionFragment9;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment1;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment10;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment2;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment3;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment4;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment5;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment6;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment7;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment8;
import com.example.logika.HomeFragment.gameActivities.logiQuiz.CircuitFragments.SimulationFragment9;
import com.example.logika.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_DIFFICULTY = "difficulty";

    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView txtDifficulty;
    private TextView textViewScore;
    private TextView textViewQuestionCount;

    private Button btnBack;

    private List<ImageView> circleDisplayList;
    private ImageView circleDisplay1;
    private ImageView circleDisplay2;
    private ImageView circleDisplay3;
    private ImageView circleDisplay4;
    private ImageView circleDisplay5;

    private TextView textViewTimer;
    private ProgressBar timerBar;

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private ImageView imageViewChoice1;
    private RadioButton rb2;
    private ImageView imageViewChoice2;
    private RadioButton rb3;
    private ImageView imageViewChoice3;
    private Button buttonConfirmNext;
    private ImageView imageViewConfirmButton;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment;
    private Fragment simulationFragment;

    private String difficulty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_quiz);

        initializeViewElements();
        configureBackButton();

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewTimer.getTextColors();

        Intent intent = getIntent();
        if (intent.hasExtra(LogicGate.EXTRA_DIFFICULTY)) {
            difficulty = intent.getStringExtra(LogicGate.EXTRA_DIFFICULTY);
        } else if (intent.hasExtra(EndActivity.EXTRA_DIFFICULTY)) {
            difficulty = intent.getStringExtra(EndActivity.EXTRA_DIFFICULTY);
        }

        txtDifficulty.setText("Difficulty: " + difficulty);


        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getQuestionsWithDifficulty(difficulty);
        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void initializeViewElements(){
        txtDifficulty = findViewById(R.id.txtDifficulty);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtQuestionNumber);
        btnBack = findViewById(R.id.btnBack);
        circleDisplay1 = findViewById(R.id.circle1);
        circleDisplay2 = findViewById(R.id.circle2);
        circleDisplay3 = findViewById(R.id.circle3);
        circleDisplay4 = findViewById(R.id.circle4);
        circleDisplay5 = findViewById(R.id.circle5);
        textViewTimer = findViewById(R.id.txtTimer);
        timerBar = findViewById(R.id.timerBar);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButtonOption1);
        imageViewChoice1 = findViewById(R.id.imageViewChoice1);
        rb2 = findViewById(R.id.radioButtonOption2);
        imageViewChoice2 = findViewById(R.id.imageViewChoice2);
        rb3 = findViewById(R.id.radioButtonOption3);
        imageViewChoice3 = findViewById(R.id.imageViewChoice3);
        buttonConfirmNext = findViewById(R.id.btnConfirmAnswer);
        imageViewConfirmButton = findViewById(R.id.imageViewConfirmButton);
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
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rbGroup.clearCheck();

        configureRadioButtonState();

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            fragment = createFragmentBasedOnType(currentQuestion.getCircuitFragment());
            simulationFragment = createSimulationFragment(currentQuestion.getCircuitFragment());

            fragmentManager.beginTransaction()
                    .replace(R.id.questionFragmentContainer, fragment.getClass(), null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name")
                    .commit();
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            imageViewConfirmButton.setImageResource(R.drawable.lq_025);


            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
    }

    private void configureRadioButtonState() {

        imageViewChoice1.setImageResource(R.drawable.lq_008);
        imageViewChoice2.setImageResource(R.drawable.lq_008);
        imageViewChoice3.setImageResource(R.drawable.lq_008);
        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice1.setImageResource(R.drawable.lq_009);
                    // Add logic for Option 2
                } else {
                    imageViewChoice1.setImageResource(R.drawable.lq_008);
                }
            }
        });

        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice2.setImageResource(R.drawable.lq_009);
                    // Add logic for Option 2
                } else {
                    imageViewChoice2.setImageResource(R.drawable.lq_008);
                }
            }
        });

        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice3.setImageResource(R.drawable.lq_009);
                    // Add logic for Option 2
                } else {
                    imageViewChoice3.setImageResource(R.drawable.lq_008);
                }
            }
        });
    }

    private void startCountDown() {
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

        textViewTimer.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewTimer.setTextColor(Color.RED);
        } else {
            textViewTimer.setTextColor(textColorDefaultCd);
        }
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
            case 3:
                fragment = new QuestionFragment3();
                break;
            case 4:
                fragment = new QuestionFragment4();
                break;
            case 5:
                fragment = new QuestionFragment5();
                break;
            case 6:
                fragment = new QuestionFragment6();
                break;
            case 7:
                fragment = new QuestionFragment7();
                break;
            case 8:
                fragment = new QuestionFragment8();
                break;
            case 9:
                fragment = new QuestionFragment9();
                break;
            case 10:
                fragment = new QuestionFragment10();
                break;
            default:
                break;
        }

        return fragment;

    }

    public Fragment createSimulationFragment(int circuitFragment){
        Fragment fragment = null;

        switch (circuitFragment) {
            case 1:
                fragment = new SimulationFragment1();
                break;
            case 2:
                fragment = new SimulationFragment2();
                break;
            case 3:
                fragment = new SimulationFragment3();
                break;
            case 4:
                fragment = new SimulationFragment4();
                break;
            case 5:
                fragment = new SimulationFragment5();
                break;
            case 6:
                fragment = new SimulationFragment6();
                break;
            case 7:
                fragment = new SimulationFragment7();
                break;
            case 8:
                fragment = new SimulationFragment8();
                break;
            case 9:
                fragment = new SimulationFragment9();
                break;
            case 10:
                fragment = new SimulationFragment10();
                break;
            default:
                break;
        }

        return fragment;

    }


    private void checkAnswer() {
        answered = true;

        countDownTimer.cancel();
        circleDisplayList = new ArrayList<>();
        circleDisplayList.add(circleDisplay1);
        circleDisplayList.add(circleDisplay2);
        circleDisplayList.add(circleDisplay3);
        circleDisplayList.add(circleDisplay4);
        circleDisplayList.add(circleDisplay5);

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);

            showCorrectAnswerDialog();
            circleDisplayList.get(questionCounter - 1).setImageResource(R.drawable.lq_004);
        } else {

            showWrongAnswerDialog();
            circleDisplayList.get(questionCounter - 1).setImageResource(R.drawable.lq_005);
        }

        showSolution();

    }

    private void showSolution() {
        imageViewChoice1.setImageResource(R.drawable.lq_010);
        imageViewChoice2.setImageResource(R.drawable.lq_010);
        imageViewChoice3.setImageResource(R.drawable.lq_010);

        fragmentManager.beginTransaction()
                .replace(R.id.questionFragmentContainer, simulationFragment.getClass(), null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                imageViewChoice1.setImageResource(R.drawable.lq_011);
                break;
            case 2:
                imageViewChoice2.setImageResource(R.drawable.lq_011);
                break;
            case 3:
                imageViewChoice3.setImageResource(R.drawable.lq_011);
                break;
        }

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);

        if (questionCounter < questionCountTotal) {
            imageViewConfirmButton.setImageResource(R.drawable.lq_013);
        } else {
            imageViewConfirmButton.setImageResource(R.drawable.lq_026);
        }


    }

    private void showCorrectAnswerDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lq_018);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        dialog.setContentView(R.layout.correct_answer_dialog_logiquiz);

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
        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lq_019);

        int newWidth = 300;
        int newHeight = 330;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        dialog.setContentView(R.layout.wrong_answer_dialog_logiquiz);

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

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lq_022);

        int newWidth = 360;
        int newHeight = 360;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        dialog.setContentView(R.layout.leave_game_dialog_logiquiz);


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
                Intent intent = new Intent(QuizActivity.this, LogicGate.class);
                startActivity(intent);

                finish();
            }
        });

        dialog.show();
    }


    private void finishQuiz() {
        Intent endIntent = new Intent(QuizActivity.this, EndActivity.class);
        endIntent.putExtra(EXTRA_SCORE, score);
        endIntent.putExtra(EXTRA_DIFFICULTY, difficulty);
        startActivity(endIntent);

        finish();
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