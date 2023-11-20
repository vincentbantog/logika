package com.example.logika.HomeFragment.gameActivities.TorF.twoPlayers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
    private TextView txtPlayerTurnDisplay;
    private ImageView imageViewPlayerTurnDisplay;

    private Dialog player1WinDialog;
    private Dialog player2WinDialog;
    private Dialog drawDialog;
    private Dialog resetDialog;
    private Dialog leaveDialog;




    private boolean isCorrect;

    private View clickedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);

        btnBack = findViewById(R.id.btnBack);
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
                showLeaveGameDialog();
            }
        });

        Button buttonReset = findViewById(R.id.TTT_btnReset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showResetDialog();
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
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        showPlayer1WinDialog();
    }
    private void player2Wins(){
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        showPlayer2WinDialog();
    }
    private void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        showDrawDialog();
    }


    private void resetBoard() {
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

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;

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



    private void showPlayer1WinDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tc_014);

        int newWidth = 350;
        int newHeight = 350;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        player1WinDialog = new Dialog(this);
        player1WinDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        player1WinDialog.setContentView(R.layout.player_1_win_dialog_torf2);


        Button btnRematch = player1WinDialog.findViewById(R.id.btnRematch);
        Button btnHome = player1WinDialog.findViewById(R.id.btnHome);

        btnRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                player1WinDialog.dismiss();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, TicTacToeMain.class);
                startActivity(intent);

                finish();
            }
        });

        player1WinDialog.show();
    }

    private void showPlayer2WinDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tc_016);

        int newWidth = 350;
        int newHeight = 350;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        player2WinDialog = new Dialog(this);
        player2WinDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        player2WinDialog.setContentView(R.layout.player_1_win_dialog_torf2);


        Button btnRematch = player2WinDialog.findViewById(R.id.btnRematch);
        Button btnHome = player2WinDialog.findViewById(R.id.btnHome);

        btnRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                player2WinDialog.dismiss();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, TicTacToeMain.class);
                startActivity(intent);

                finish();
            }
        });

        player2WinDialog.show();
    }

    private void showDrawDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tc_026);

        int newWidth = 350;
        int newHeight = 350;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        drawDialog = new Dialog(this);
        drawDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        drawDialog.setContentView(R.layout.player_1_win_dialog_torf2);


        Button btnRematch = drawDialog.findViewById(R.id.btnRematch);
        Button btnHome = drawDialog.findViewById(R.id.btnHome);

        btnRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                drawDialog.dismiss();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicTacToeGame.this, TicTacToeMain.class);
                startActivity(intent);

                finish();
            }
        });

        drawDialog.show();
    }

    private void showResetDialog(){

        Bitmap customBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tc_011);

        int newWidth = 350;
        int newHeight = 350;

        Bitmap resizedBitmap = Bitmap.createScaledBitmap(customBackgroundBitmap, newWidth, newHeight, true);

        BitmapDrawable customBackgroundDrawable = new BitmapDrawable(getResources(), resizedBitmap);

        resetDialog = new Dialog(this);
        resetDialog.getWindow().setBackgroundDrawable(customBackgroundDrawable);
        resetDialog.setContentView(R.layout.reset_game_dialog_torf2);


        Button btnReset = resetDialog.findViewById(R.id.btnReset);
        Button btnBack = resetDialog.findViewById(R.id.btnBack);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
                resetDialog.dismiss();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDialog.dismiss();
            }
        });

        resetDialog.show();
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
                Intent intent = new Intent(TicTacToeGame.this, TicTacToeMain.class);
                startActivity(intent);

                finish();
            }
        });

        leaveDialog.show();
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