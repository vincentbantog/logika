package com.example.logika.gameActivities.logiQuiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment1;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment2;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment3;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment4;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.QuestionFragment5;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.SimulationFragment1;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.SimulationFragment2;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.SimulationFragment3;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.SimulationFragment4;
import com.example.logika.gameActivities.logiQuiz.CircuitFragments.SimulationFragment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView textViewScore;
    private TextView textViewQuestionCount;
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
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

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

    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        initializeViewElements();

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewTimer.getTextColors();


        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestion();
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
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtQuestionNumber);
        circleDisplay1 = findViewById(R.id.circle1);
        circleDisplay2 = findViewById(R.id.circle2);
        circleDisplay3 = findViewById(R.id.circle3);
        circleDisplay4 = findViewById(R.id.circle4);
        circleDisplay5 = findViewById(R.id.circle5);
        textViewTimer = findViewById(R.id.txtTimer);
        timerBar = findViewById(R.id.timerBar);
        rbGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.radioButtonOption1);
        rb2 = findViewById(R.id.radioButtonOption2);
        rb3 = findViewById(R.id.radioButtonOption3);
        buttonConfirmNext = findViewById(R.id.btnConfirmAnswer);
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rbGroup.clearCheck();

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
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
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

            circleDisplayList.get(questionCounter - 1).setImageResource(R.drawable.progress_tracker_correct_circle_small);
        } else {
            circleDisplayList.get(questionCounter - 1).setImageResource(R.drawable.progress_tracker_correct_circle_wrong);
        }

        showSolution();

    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);

        fragmentManager.beginTransaction()
                .replace(R.id.questionFragmentContainer, simulationFragment.getClass(), null)
                .setReorderingAllowed(true)
                .addToBackStack("name")
                .commit();

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }

        rb1.setEnabled(false);
        rb2.setEnabled(false);
        rb3.setEnabled(false);

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }


    }

    private void finishQuiz() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            Intent intent = new Intent(QuizActivity.this, LogicGate.class);
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