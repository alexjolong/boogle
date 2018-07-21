package com.alexjolong.sandbox.boogle;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class BoardManager {
    private GameplayManager mgpm;
    private Context mBoardContext;
    private TableLayout mBoard;
    private int mBoardDim;

    public BoardManager(Context c, GameplayManager gpm, TableLayout boardToPlay, int boardDim) {
        mBoardContext = c;
        mgpm = gpm;
        mBoard = boardToPlay;
        mBoardDim = boardDim;
        createBoard();
    }

    private void createBoard() {
        //Should only be called once, by the class constructor
        // Create each of the squares on the board
        for (int i = 0; i < mBoardDim; i++) {
            TableRow tr = new TableRow(mBoardContext);
            mBoard.addView(tr);

            for (int j = 0; j < mBoardDim; j++) {
                Button newLetter = new Button(mBoardContext);

                int buttonIndex = (mBoardDim*i) + j;
                newLetter.setId(buttonIndex);
                newLetter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        tapBoardButton((Button) v);
                    }
                });

                tr.addView(newLetter);
            }
        }
        return 0;
    }

    public void setBoard() {
        //set the board with fresh values
        int rowCount = mBoard.getChildCount();
        for (int i = 0; i < rowCount; i++) {
            TableRow row = (TableRow) mBoard.getChildAt(i);
            int buttonCount = row.getVirtualChildCount();
            Log.i("board", "number of letters in row " + i + " is " + buttonCount);

            for (int j = 0; j < buttonCount; j++) {
                Button btn = (Button) row.getVirtualChildAt(j);
                CharSequence newLetterText = mgpm.chooseLetterEngine();
                btn.setText(newLetterText);
            }
        }
    }

    public void setBoard(TableLayout boardToSet) {
        mBoard = boardToSet;
    }

    public TableLayout getBoard() {
        return mBoard;
    }



    public void tapBoardButton(Button buttonView) {
        Log.i("board","Board with id " + buttonView.getId() + " and text  " +
                buttonView.getText() + " was tapped.");
        //TODO: is the button valid according to the current word?

        // If so, add the new button's index to the current word
        mgpm.addLetterToCurrentWord(buttonView.getId());

        return;
    }


}
