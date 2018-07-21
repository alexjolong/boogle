package com.alexjolong.sandbox.boogle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TableLayout mBoard;
    Button checkButton;
    Button endGameButton;
    TextView currentWordText;
    TextView scoreText;
    private int mBoardDim = 4;
    int mPoints;
    /*
    *  For now, we will store the current word as a list of indices into the board.
     * The maximum length of a word is equal to mBoardDim, but the word could be smaller.
     * Of course, the word should be erased if the board is cleared, but this will allow
     * us to do some clever math in order to figure out if a word is made of
     * "consecutive" board tiles.
     */
    ArrayList<Integer> currentWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mPoints = 0;
        currentWord = new ArrayList<>();
        mBoard = findViewById(R.id.mBoard);
        checkButton = findViewById(R.id.check_word_button);
        endGameButton = findViewById(R.id.end_game_button);
        currentWordText = findViewById(R.id.current_word);
        scoreText = findViewById(R.id.score);

        GameplayManager gpm = new GameplayManager(2);
        BoardManager bm = new BoardManager(this, gpm, mBoard, mBoardDim);

        updateCurrentWordDisplay();
        updatePointsDisplay();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int pts = getPointsForWord(getCurrentWord());
                addPoints(pts);
                clearCurrentWord();
            }
        });

        endGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                System.out.println("End game button was tapped");
                //TODO: add "are you sure you want to end your game?" confirmation
                clearCurrentWord();
                int finalScore = getPoints();
                //TODO: do something with final score. Store and rank
                setPoints(0);
                refreshBoard(mBoardDim, mBoardDim, mBoard);
            }
        });
    }

    public int refreshBoard(int rows, int cols, TableLayout board) {


    }


    public void setCurrentWord(ArrayList<Integer> newWord) {
        // Set the new word internally
        currentWord.clear();
        currentWord.addAll(newWord);

        // Display the new word
        updateCurrentWordDisplay();
        return;
    }

    public void clearCurrentWord() {
        currentWord.clear();
        updateCurrentWordDisplay();
        return;
    }

    public void updateCurrentWordDisplay() {
        currentWordText.setText(getCurrentWord());
        return;
    }

    public void updatePointsDisplay() {
        scoreText.setText("" + getPoints());
    }

    public String getCurrentWord() {
        String currWord = "";
        for (int ind : currentWord) {
            Button clickedButton = findViewById(ind);
            currWord = currWord + clickedButton.getText();
        }
        return currWord;
    }

    public int getPointsForWord(String word) {
        //TODO: webservice call to check whether current word is valid
        // for now, just assume word is valid every time.
        return word.length();
    }

    public int setPoints(int pts) {
        mPoints = pts;
        scoreText.setText("" + getPoints());
        return mPoints;
    }

    public int addPoints(int pts) {
        mPoints += pts;
        scoreText.setText("" + getPoints());
        return mPoints;
    }

    public int getPoints() {
        return mPoints;
    }

}
