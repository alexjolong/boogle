package com.alexjolong.sandbox.boogle;

import android.util.Log;

import java.util.Map;
import java.util.Random;

public class GameplayManager {
    /* Modes:
     * 1 - truly random letters picked
     * 2 - letters picked according to their occurrence frequency
     *     (see https://en.wikipedia.org/wiki/Letter_frequency )
     */
    private int mmode = 1;

    private String mAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int mAlphabetLength = 26;
    private int mAlphabetFrequencyMap[] = {
            122 /* A */,
            22  /* B */,
            41  /* C */,
            63  /* D */,
            190 /* E */,
            33  /* F */,
            30  /* G */,
            91  /* H */,
            104 /* I */,
            2   /* J */,
            11  /* K */,
            60  /* L */,
            36  /* M */,
            101 /* N */,
            112 /* O */,
            28  /* P */,
            1   /* Q */,
            89  /* R */,
            94  /* S */,
            135 /* T */,
            41  /* U */,
            14  /* V */,
            35  /* W */,
            2   /* X */,
            29  /* Y */,
            1   /* Z */};
    private int mSumFrequencyMap = 1487;

    public GameplayManager(int gamePlayMode) {
        mmode = gamePlayMode;
    }

    public String chooseLetterEngine() {
        Random rand = new Random();
        String newLetter = null;

        if (mmode == 1) {
            int charIndex = rand.nextInt(mAlphabetLength);
            Character newChar = mAlphabet.charAt(charIndex);
            newLetter = newChar.toString();
            Log.i("gameplay", "New letter: " + newLetter);
        }
        else if (mmode == 2) {
            int charIndex = rand.nextInt(mSumFrequencyMap);
            int currentIndex = 0;

            for (int letterIndex = 0; letterIndex < mAlphabetLength; letterIndex++ ) {
                Character newChar = mAlphabet.charAt(letterIndex);
                int charFreq = mAlphabetFrequencyMap[letterIndex];
                currentIndex += charFreq;
                if (charIndex <= mSumFrequencyMap) {
                    newLetter = newChar.toString();
                    Log.i("gameplay", "New letter: " + newLetter);
                    break;
                }
                else {

                }
            }
        }
        else {
            Log.i("gameplay", "Unexpected mode. Got mode " + mmode);
        }

        return newLetter;
    }

    /*
     * TODO: displayManager class
     * should include:
     * updateCurrentWordDisplay
     * upDatePointsDisplay
     */

    /*
     * TODO: bring all of the gameplay stuff (word, points, etc.)
     * Not any front-end text views. Those will all go into displayManager class)
     */

    public void addLetterToCurrentWord(int newCharIndex) {
        // Set the new word internally
        currentWord.add(newCharIndex);

        // Display the new word
        updateCurrentWordDisplay();
        return;
    }


}
