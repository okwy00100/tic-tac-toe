package com.okwy.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Okwy Nwachukwu on 2020-02-24.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button[][] buttons = new Button[3][3];

    TextView playerOneScore;
    TextView playerTwoScore;
    TextView drawCountScore;
    Button resetButton;
    Button newGame;

    boolean playerOneTurn = true;

    int playerOnePoints;
    int playerTwoPoints;
    int drawCount;
    int turnCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneScore = findViewById(R.id.txt_p1);
        playerTwoScore = findViewById(R.id.txt_p2);
        drawCountScore = findViewById(R.id.txt_draw);
        resetButton = findViewById(R.id.reset);
        newGame = findViewById(R.id.newGame);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                System.out.println("resID for i[" + i + "] and j[" + j + "] is: " + resID);
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();

            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (playerOneTurn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        turnCount++;

        if (checkForWin()) {
            if (playerOneTurn) {
                playerOneWins();
            } else {
                playerTwoWins();
            }

        } else if (turnCount == 9) {
            draw();
        } else {
            playerOneTurn = !playerOneTurn;
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
            /** Row check */
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                buttons[i][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[i][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[i][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;
            }
            /** Column check */
            else if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")){

                buttons[0][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[1][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[2][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;

            }
            /** Top left diagonal check */
            else if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")){
                buttons[0][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[2][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;

            }
            /** Top right diagonal check */
            else if(field[0][2].equals(field[1][1]) && field[0][0].equals(field[2][0]) && !field[0][2].equals("")){
                buttons[0][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[2][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;
            }
            /** Bottom left diagonal check */
            else if(field[2][0].equals(field[1][1]) && field[2][0].equals(field[0][2]) && !field[2][0].equals("")){
                buttons[2][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[0][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;
            }
            /** Bottom right diagonal check */
            else if(field[2][2].equals(field[1][1]) && field[2][2].equals(field[0][0]) && !field[2][2].equals("")){
                buttons[2][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
                buttons[0][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
                return true;
            }


        }

//        for (int i = 0; i < 3; i++) {
//            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
//                buttons[0][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[1][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[2][i].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                return true;
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
//                buttons[0][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[2][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                return true;
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            if (field[0][2].equals(field[1][1]) && field[0][0].equals(field[2][0]) && !field[0][2].equals("")) {
//                buttons[0][2].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[1][1].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                buttons[2][0].setTextColor(this.getResources().getColor(R.color.colorAccent));
//                return true;
//            }
//        }


        return false;

    }

    private void newGame() {
        resetBoard();

    }

    private void playerOneWins() {
        playerOnePoints++;
        Toast.makeText(this, "Player One WINS!!!!!", Toast.LENGTH_SHORT).show();
        updateScoreBoard();
    }

    private void playerTwoWins() {
        playerTwoPoints++;
        Toast.makeText(this, "Player Two WINS!!!!!", Toast.LENGTH_SHORT).show();
        updateScoreBoard();

    }

    private void draw() {
        drawCount++;
        Toast.makeText(this, "It's a DRAW!!!!!", Toast.LENGTH_SHORT).show();
        updateScoreBoard();

    }

    private void updateScoreBoard() {
        playerOneScore.setText("Player 1: " + playerOnePoints);
        playerTwoScore.setText("Player 2: " + playerTwoPoints);
        drawCountScore.setText("Draws: " + drawCount);

    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setTextColor(getResources().getColor(R.color.colorBlack));
            }
        }
        turnCount = 0;
        playerOneTurn = true;


    }

    private void resetGame() {

        playerOnePoints = 0;
        playerTwoPoints = 0;
        drawCount = 0;
        updateScoreBoard();
        resetBoard();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("playerOnePoints", playerOnePoints);
        outState.putInt("playerTwoPoints", playerTwoPoints);
        outState.putInt("drawCount", drawCount);
        outState.putInt("turnCount", turnCount);
        outState.putBoolean("playerOneTurn", playerOneTurn);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        playerOnePoints = savedInstanceState.getInt("playerOnePoints");
        playerTwoPoints = savedInstanceState.getInt("playerTwoPoints");
        drawCount = savedInstanceState.getInt("drawCount");
        turnCount = savedInstanceState.getInt("turnCount");
        playerOneTurn = savedInstanceState.getBoolean("playerOneTurn");

    }

    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_tic_tac_toe)
                .setTitle("Tic-Tac-Toe")
                .setMessage("Do you want to quit this application?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }
}
