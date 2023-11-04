package com.example.logika.gameActivities.basiQuiz;

import static android.graphics.PorterDuff.Mode.SRC_IN;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BQQuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    public static final String EXTRA_EASY_SCORE = "extraEasyScore";
    public static final String EXTRA_MEDIUM_SCORE = "extraMediumScore";
    public static final String EXTRA_HARD_SCORE = "extraHardScore";
    private TextView txtEasyScore;
    private TextView txtQuestionNumber;
    private TextView txtQuestion;
    private TextView txtScore;
    private Button btnBack;

    private ProgressBar scoreBar;
    private List<ImageView> progressBarImageList;
    private ImageView imageViewProgressBar1;
    private ImageView imageViewProgressBar2;
    private ImageView imageViewProgressBar3;
    private ImageView imageViewProgressBar4;
    private ImageView imageViewProgressBar5;
    private ImageView imageViewProgressBar6;
    private ImageView imageViewProgressBar7;
    private ImageView imageViewProgressBar8;
    private ImageView imageViewProgressBar9;
    private ImageView imageViewProgressBar10;


    private ColorStateList textColorDefaultRb;
    private RadioGroup rbGroup;
    private RadioButton radioBtnChoice1;
    private ImageView imageViewChoice1;
    private RadioButton radioBtnChoice2;
    private ImageView imageViewChoice2;
    private RadioButton radioBtnChoice3;
    private ImageView imageViewChoice3;
    private RadioButton radioBtnChoice4;
    private ImageView imageViewChoice4;
    private Button btnConfirm;

    private int answerNr;

    private List<BQQuestion> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private boolean answered;
    private int score;
    private BQQuestion currentQuestion;

    private int easyScore;
    private int mediumScore;
    private int hardScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bqquiz);

        initializeViewElements();
        configureBackButton();

        textColorDefaultRb = radioBtnChoice1.getTextColors();


        BQQuizDbHelper dbHelper = new BQQuizDbHelper(this);

        questionList = new ArrayList<>();
        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Easy", 3));
        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Medium", 4));
        questionList.addAll(dbHelper.getQuestionsWithDifficultyAndCount("Hard", 3));

        questionCountTotal = questionList.size();


        showNextQuestion();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (radioBtnChoice1.isChecked() || radioBtnChoice2.isChecked() || radioBtnChoice3.isChecked() || radioBtnChoice4.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(BQQuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void initializeViewElements() {
        txtEasyScore = findViewById(R.id.txtEasyScore);
        txtQuestionNumber = findViewById(R.id.txtQuestionNumber);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtScore = findViewById(R.id.txtBQScore);
        btnBack = findViewById(R.id.btnBack);

        scoreBar = findViewById(R.id.scoreBar);
        imageViewProgressBar1 = findViewById(R.id.imageViewProgressBar1);
        imageViewProgressBar2 = findViewById(R.id.imageViewProgressBar2);
        imageViewProgressBar3 = findViewById(R.id.imageViewProgressBar3);
        imageViewProgressBar4 = findViewById(R.id.imageViewProgressBar4);
        imageViewProgressBar5 = findViewById(R.id.imageViewProgressBar5);
        imageViewProgressBar6 = findViewById(R.id.imageViewProgressBar6);
        imageViewProgressBar7 = findViewById(R.id.imageViewProgressBar7);
        imageViewProgressBar8 = findViewById(R.id.imageViewProgressBar8);
        imageViewProgressBar9 = findViewById(R.id.imageViewProgressBar9);
        imageViewProgressBar10 = findViewById(R.id.imageViewProgressBar10);

        rbGroup = findViewById(R.id.radioGroupChoices);
        radioBtnChoice1 = findViewById(R.id.radioBtnOption1);
        imageViewChoice1 = findViewById(R.id.imageViewChoice1);
        radioBtnChoice2 = findViewById(R.id.radioBtnOption2);
        imageViewChoice2 = findViewById(R.id.imageViewChoice2);
        radioBtnChoice3 = findViewById(R.id.radioBtnOption3);
        imageViewChoice3 = findViewById(R.id.imageViewChoice3);
        radioBtnChoice4 = findViewById(R.id.radioBtnOption4);
        imageViewChoice4 = findViewById(R.id.imageViewChoice4);

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


        radioBtnChoice1.setTextColor(textColorDefaultRb);
        radioBtnChoice2.setTextColor(textColorDefaultRb);
        radioBtnChoice3.setTextColor(textColorDefaultRb);
        radioBtnChoice4.setTextColor(textColorDefaultRb);
        radioBtnChoice1.setEnabled(true);
        radioBtnChoice2.setEnabled(true);
        radioBtnChoice3.setEnabled(true);
        radioBtnChoice4.setEnabled(true);
        rbGroup.clearCheck();

        configureRadioButtonState();

        if (questionCounter < questionCountTotal) {

            currentQuestion = questionList.get(questionCounter);

            txtQuestion.setText(currentQuestion.getQuestion());
            radioBtnChoice1.setText(currentQuestion.getOption1());
            radioBtnChoice2.setText(currentQuestion.getOption2());
            radioBtnChoice3.setText(currentQuestion.getOption3());
            radioBtnChoice4.setText(currentQuestion.getOption4());


            questionCounter++;
            txtQuestionNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnConfirm.setText("Confirm");


        } else {
            finishQuiz();
        }

    }

    public void configureRadioButtonState(){
        radioBtnChoice1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice1.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice1.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice2.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice2.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice3.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice3.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });

        radioBtnChoice4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Change the ImageView for Option 2
                    imageViewChoice4.setImageResource(R.drawable.basiquiz_radio_button_on_state);
                    // Add logic for Option 2
                } else {
                    imageViewChoice4.setImageResource(R.drawable.basiquiz_radio_button_default_state);
                }
            }
        });
    }
    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        progressBarImageList = new ArrayList<>();
        progressBarImageList.add(imageViewProgressBar1);
        progressBarImageList.add(imageViewProgressBar2);
        progressBarImageList.add(imageViewProgressBar3);
        progressBarImageList.add(imageViewProgressBar4);
        progressBarImageList.add(imageViewProgressBar5);
        progressBarImageList.add(imageViewProgressBar6);
        progressBarImageList.add(imageViewProgressBar7);
        progressBarImageList.add(imageViewProgressBar8);
        progressBarImageList.add(imageViewProgressBar9);
        progressBarImageList.add(imageViewProgressBar10);

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            txtScore.setText("Score: " + score);
            updateScoreBar();

            String difficulty = currentQuestion.getDifficulty();
            if ("Easy".equals(difficulty)) {
                easyScore++;
            } else if ("Medium".equals(difficulty)) {
                mediumScore++;
            } else if ("Hard".equals(difficulty)){
                hardScore++;
            }


            showCorrectAnswerDialog();
            progressBarImageList.get(questionCounter - 1).setImageResource(R.drawable.image_progress_bar_basicquiz_correct);
        } else {

            showWrongAnswerDialog();
            progressBarImageList.get(questionCounter - 1).setImageResource(R.drawable.image_progress_bar_basicquiz_wrong);
        }

        showSolution();
    }

    private void updateScoreBar() {
        int progress = (int) (score * 10);
        scoreBar.setProgress(progress);
    }


    private void showSolution() {
        radioBtnChoice1.setTextColor(Color.RED);
        radioBtnChoice2.setTextColor(Color.RED);
        radioBtnChoice3.setTextColor(Color.RED);
        radioBtnChoice4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                radioBtnChoice1.setTextColor(Color.GREEN);
                break;
            case 2:
                radioBtnChoice2.setTextColor(Color.GREEN);
                break;
            case 3:
                radioBtnChoice3.setTextColor(Color.GREEN);
                break;
            case 4:
                radioBtnChoice4.setTextColor(Color.GREEN);
                break;
        }

        radioBtnChoice1.setEnabled(false);
        radioBtnChoice2.setEnabled(false);
        radioBtnChoice3.setEnabled(false);
        radioBtnChoice4.setEnabled(false);
        if (questionCounter < questionCountTotal) {
            btnConfirm.setText("Next");
        } else {
            btnConfirm.setText("Finish");
        }
    }

    private void showCorrectAnswerDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.correct_answer_dialog_basiquiz);

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
        dialog.setContentView(R.layout.wrong_answer_dialog_basiquiz);

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
                Intent intent = new Intent(BQQuizActivity.this, MultipleChoice.class);
                startActivity(intent);

                finish();
            }
        });

        dialog.show();
    }

    private void finishQuiz() {
        Intent intent = new Intent(BQQuizActivity.this, BQEndActivity.class);
        intent.putExtra(EXTRA_SCORE, score);
        intent.putExtra(EXTRA_EASY_SCORE, easyScore);
        intent.putExtra(EXTRA_MEDIUM_SCORE, mediumScore);
        intent.putExtra(EXTRA_HARD_SCORE, hardScore);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        showLeaveGameDialog();
    }
}