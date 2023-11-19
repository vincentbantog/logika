package com.example.logika.gameActivities.TorF.twoPlayers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logika.R;

public class TicTacToeGame extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_CHECKER = "extraChecker";
    public static final int REQUEST_CODE_TTT = 1;

    private Button[][] buttons = new Button[3][3];

    private Boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private Button btnBack;
    private TextView txtScorePlayer1;
    private TextView txtScorePlayer2;
    private TextView txtPlayerTurnDisplay;
    private ImageView imageViewPlayerTurnDisplay;



    private boolean isCorrect;
    private TextView txtLastAnswerChecker;

    private View clickedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);

        btnBack = findViewById(R.id.btnBack);
        txtScorePlayer1 = findViewById(R.id.TTT_txtScorePlayer1);
        txtScorePlayer2 = findViewById(R.id.TTT_txtScorePlayer2);
        txtLastAnswerChecker = findViewById(R.id.txtLastAnswerChecker);
        txtPlayerTurnDisplay = findViewById(R.id.TTT_txtPlayerTurnDisplay);
        imageViewPlayerTurnDisplay = findViewById(R.id.imageViewPlayerTurnIndicator);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "TTT_btn_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button buttonReset = findViewById(R.id.TTT_btnReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resetGame();
            }
        });

    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")){
            return;
        }
        showQuestion();


        clickedView = v;

        roundCount++;
    }



    private void showQuestion() {
        Intent intent = new Intent(TicTacToeGame.this, TicTacToeQuiz.class);
        startActivityForResult(intent, REQUEST_CODE_TTT);

        if (!player1Turn) {
            txtPlayerTurnDisplay.setText("Player 1's Turn!");
            imageViewPlayerTurnDisplay.setImageResource(R.drawable.tc_007);
        } else {
            txtPlayerTurnDisplay.setText("Player 2's Turn!");
            imageViewPlayerTurnDisplay.setImageResource(R.drawable.tc_008);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TTT) {
            if (resultCode == RESULT_OK) {
                isCorrect = data.getBooleanExtra(KEY_CHECKER, false);
                if (isCorrect) {
                    txtLastAnswerChecker.setText("Last Answer: Correct Answer!");
                } else {
                   txtLastAnswerChecker.setText("Last Answer: Wrong Answer!");
                }

                if (player1Turn) {
                    if (isCorrect) {
                        ((Button) clickedView).setText("X");
                    } else {
                        ((Button) clickedView).setText("");
                    }
                } else {
                    if (isCorrect) {
                        ((Button) clickedView).setText("O");
                    } else {
                        ((Button) clickedView).setText("");
                    }
                }

                if (checkForWin()) {
                    if (player1Turn) {
                        player1Wins();
                    } else {
                        player2Wins();
                    }
                } else if (roundCount == 9) {
                    draw();
                } else {
                    player1Turn = !player1Turn;
                }


            }
            if (resultCode == RESULT_CANCELED) {
                txtLastAnswerChecker.setText("Nothing Received");
            }
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }



    private void player1Wins(){
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void player2Wins(){
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        txtScorePlayer1.setText("Player 1: " + player1Points);
        txtScorePlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;

        player1Turn = !player1Turn;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;

        player1Turn = true;
        txtPlayerTurnDisplay.setText("Player 1's Turn!");
        imageViewPlayerTurnDisplay.setImageResource(R.drawable.tc_007);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }




    @Override
    public void onBackPressed() {

    }
}