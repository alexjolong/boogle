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

    //TODO: Create a letter frequency dictionary so that common letters appear more frequently
    String mAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    TableLayout mBoard;
    Button checkButton;
    Button endGameButton;
    TextView currentWordText;
    TextView scoreText;
    int mBoardDim = 4;
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

        int res = createBoard(mBoardDim, mBoardDim, mBoard);
        if (res != 0) {
            //TODO: raise error
            return;
        }
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

    public int createBoard(int rows, int cols, TableLayout board) {

        // Create each of the squares on the board
        for (int i = 0; i < rows; i++) {
            TableRow tr = new TableRow(this);
            board.addView(tr);

            for (int j = 0; j < cols; j++) {
                int buttonIndex = (i*rows) + j;
                Button newLetter = new Button(this);
                CharSequence newLetterText = chooseLetterEngine();
                newLetter.setText(newLetterText);
                newLetter.setId(buttonIndex);
                newLetter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        System.out.println("Board tile was tapped");
                        tapBoardButton((Button) v);
                    }
                });
                tr.addView(newLetter);
            }
        }
        return 0;
    }

    public int refreshBoard(int rows, int cols, TableLayout board) {

        // Create each of the squares on the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int buttonIndex = (i*rows) + j;
                Button newLetter = findViewById(buttonIndex);
                CharSequence newLetterText = chooseLetterEngine();
                newLetter.setText(newLetterText);
            }
        }
        return 0;
    }

    public CharSequence chooseLetterEngine() {
        Random rand = new Random();
        int alphabetLength = mAlphabet.length();
        int charIndex = rand.nextInt(alphabetLength);
        Character newChar = mAlphabet.charAt(charIndex);
        CharSequence newLetter = newChar.toString();
        return newLetter;
    }

    public void tapBoardButton(Button buttonView) {
        System.out.println("Tapped letter: " + buttonView.getText() );
        //TODO: is the button valid according to the current word?

        // If so, add the new button's index to the current word
        addLetterToCurrentWord(buttonView.getId());

        return;
    }

    public void addLetterToCurrentWord(int newCharIndex) {
        // Set the new word internally
        currentWord.add(newCharIndex);

        // Display the new word
        updateCurrentWordDisplay();
        return;
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
