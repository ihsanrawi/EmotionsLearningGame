package com.example.emotionslearninggame.Level3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.emotionslearninggame.EndLevel;
import com.example.emotionslearninggame.NextLevel;
import com.example.emotionslearninggame.R;

import java.util.Random;

public class MemoryPuzzle extends AppCompatActivity implements View.OnClickListener {
    GridLayout mpBoard;
    TextView score_TextView;
    Toolbar toolbar;
    int numCol, numRow, numOfElements, getScore, pairsMatched,score;

    int[] images;
    int[] tile;

    private Tiles firstTiles, secondTiles;
    private boolean isProcess = false;

    private Chronometer chronometer;
    private long timeWhenStopped;
    private boolean notRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_puzzle);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mpBoard = (GridLayout)findViewById(R.id.memoryPuzzleBoardGL);
        score_TextView = (TextView)findViewById(R.id.scoreTV);

        numCol = mpBoard.getColumnCount(); //numCol is 4
        numRow = mpBoard.getRowCount();    //numRow is 3

        numOfElements = numCol * numRow; //numOfElements is 12

        loadImages();
        cardRandomizer();
        setBoard();

    }

    private void loadImages() {
        /*Each element in the array
        * is assign to a drawable image
        */
        images = new int[numOfElements/2]; //images is 6

        images[0] = R.drawable.angry_emoji;
        images[1] = R.drawable.sad_emoji;
        images[2] = R.drawable.joy_emoji;
        images[3] = R.drawable.surprise_emoji;
        images[4] = R.drawable.fear_emoji;
        images[5] = R.drawable.disgust_emoji;
    }

    private void startTimer(long time) {
        long seconds = time;
        System.out.println("seconds " + seconds);
        chronometer = (Chronometer)findViewById(R.id.time_Ch);
        chronometer.setBase(SystemClock.elapsedRealtime() + seconds);
        chronometer.start();
        System.out.println("Base " + chronometer.getBase());
        notRunning = false;
        System.out.println(notRunning);
    }

    //This method is used to randomize the position of the cards
    private void cardRandomizer() {
        int i, temp, shuffle;
        Random rand = new Random();
        tile = new int[numOfElements];

        for (i = 0; i < numOfElements; i++) {
            tile[i] = i%(numOfElements/2);
        }

        for (i = 0; i <numOfElements; i++) {
            temp = tile[i];
            shuffle = rand.nextInt(numOfElements);
            Log.v("Shuffle", String.valueOf(shuffle));

            tile[i] = tile[shuffle];
            tile[shuffle] = temp;
        }
    }

    /*The game board are put with
    * images which had been randomize
    * by the cardRandomizer
    */
    private void setBoard() {
        int r, c;
        for (r = 0; r < numRow; r++) {
            for (c = 0; c < numCol; c++) {
                Tiles tmpTiles = new Tiles(this, r, c, images[tile[r * numCol + c]]);
                tmpTiles.setId(View.generateViewId());
                tmpTiles.setOnClickListener(this);
                mpBoard.addView(tmpTiles);
            }
        }
        /*get score from the previous levels*/
        Bundle getCrntScore = getIntent().getExtras();
        long getTimer = getCrntScore.getLong("timer");
        getScore = getCrntScore.getInt("score");
        score = getScore;
        score_TextView.setText(String.valueOf(score));
        timeWhenStopped = getTimer;
        startTimer(timeWhenStopped);
    }

    @Override
    public void onClick(View v) {
        Tiles tiles = (Tiles) v;

        if (isProcess) return; //Disable process user input when is processing

        if (tiles.isPaired) return;  //If the tiles already flipped and matched, ignore onclicklisterner

        if (firstTiles == null) {
            firstTiles = tiles;
            firstTiles.flipCard();
            return;
        }

        if (firstTiles.getId() == tiles.getId()) { //If the same tiles is tapped again do nothing
            return;
        }

        if (firstTiles.getFrontImageId() == tiles.getFrontImageId()){
            /*If match is found the both tiles are flipped and stays flipped.
            OnClick is then disabled.*/
            tiles.flipCard();

            tiles.setPaired(true);
            firstTiles.setPaired(true);
            score = score + 10;

            firstTiles.setEnabled(false);
            tiles.setEnabled(false);

            pairsMatched++; //Saja2
            finalScore(pairsMatched);
            firstTiles = null;
            score_TextView.setText(String.valueOf(score));
        }else {
            //If the pair is not matched, the card is flipped back
            // and no open tiles is shown.
            secondTiles = tiles;
            secondTiles.flipCard();

            isProcess = true;

            final android.os.Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    secondTiles.flipCard();
                    firstTiles.flipCard();

                    firstTiles = null;
                    secondTiles = null;

                    isProcess = false;
                }
            }, 500);
            score = score - 3;
            if (score < 0) {
                score = 0;
            }
            score_TextView.setText(String.valueOf(score));
        }
    }

    private void finalScore(int i) {
        if (i == 6){
            Intent intent = new Intent(MemoryPuzzle.this, EndLevel.class);
            Bundle a = new Bundle();
            a.putInt("score", score);
            a.putInt("timer", stopTimer(timeWhenStopped));
            intent.putExtras(a);
            startActivity(intent);
            finish();
        }
    }

    private int stopTimer(long timeTaken) {
        int seconds = 0;
        if (!notRunning) {
            timeTaken = chronometer.getBase() - SystemClock.elapsedRealtime();
            seconds = (int) timeTaken / 1000;
            chronometer.stop();
            notRunning =true;
            System.out.println(notRunning);
        }
        return Math.abs(seconds);
    }
}

