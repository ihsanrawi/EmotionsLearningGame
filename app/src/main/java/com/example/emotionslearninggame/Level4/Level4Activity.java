package com.example.emotionslearninggame.Level4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emotionslearninggame.Level6.Questions6;
import com.example.emotionslearninggame.NextLevel;
import com.example.emotionslearninggame.R;


public class Level4Activity extends AppCompatActivity implements View.OnClickListener {
    Questions4 questionLibrary = new Questions4();
    Questions6 questions6 = new Questions6();
    private TextView scoreTV;
    private ImageView question;
    private Button choice1,choice2,choice3;

    private String mAnswer;
    private int score = 0, questionID = 0, correct;
    private MediaPlayer mp;

    private Chronometer chronometer;
    private long timeTaken = 0;
    private boolean notRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);

        scoreTV = (TextView)findViewById(R.id.score_TextView);
        question = (ImageView) findViewById(R.id.EmojiIV);
        choice1 = (Button)findViewById(R.id.choice1_Button);
        choice2 = (Button)findViewById(R.id.choice2_Button);
        choice3 = (Button)findViewById(R.id.choice3_Button);

        updateQuestions();

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);

        chronometer = (Chronometer)findViewById(R.id.time_Ch);
        startTimer(timeTaken);
    }

    private void startTimer(long elapsedTime) {
        chronometer.setBase(SystemClock.elapsedRealtime() + elapsedTime);
        chronometer.start();
        notRunning = false;
        System.out.println(notRunning);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case(R.id.choice1_Button) :
                if (choice1.getText() == mAnswer) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    checkQuestionID(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    updateScore(score);
                    checkQuestionID(questionID);
                }
                break;

            case(R.id.choice2_Button) :
                if (choice2.getText() == mAnswer) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    checkQuestionID(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    updateScore(score);
                    checkQuestionID(questionID);
                }
                break;

            case(R.id.choice3_Button) :
                if (choice3.getText() == mAnswer) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    checkQuestionID(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    updateScore(score);
                    checkQuestionID(questionID);
                }
                break;
        }
    }

    private void toast(int i) {
        if (i == 0) {
            final Toast toastIncorrect = Toast.makeText(this, "The correct answer is "+mAnswer, Toast.LENGTH_SHORT);
            toastIncorrect.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toastIncorrect.cancel();
                }
            }, 400);
        } else if (i == 1) {
            final Toast toastCorrect = Toast.makeText(this, "The answer is correct!", Toast.LENGTH_SHORT);
            toastCorrect.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toastCorrect.cancel();
                }
            }, 400);
        }
    }

    private void sfx(int j) {
        int sfx = questions6.getanswerSFX(j);
        Boolean mpStatus;

        try {
            mpStatus = mp.isPlaying();
        } catch (Exception e) {
            mpStatus = false;
        }

        if (mpStatus) {
            mp.stop();
            mp.release();
        } else {
            try {
                mp = new MediaPlayer();
                mp = MediaPlayer.create(getApplicationContext(), sfx);
                mp.start();
            } catch (Exception e) {
                Log.i("Error playing sound", e.toString());
            }
        }
    }

    private void checkQuestionID(final int questionID) {
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (questionID < 6) {
                    updateQuestions();
                    //Re-enable the answer buttons
                    choice1.setEnabled(true);
                    choice2.setEnabled(true);
                    choice3.setEnabled(true);
                }else {
                    // if over 6 do this
                    Intent intent = new Intent(Level4Activity.this, NextLevel.class);
                    Bundle a = new Bundle();
                    a.putInt("score", score);
                    a.putInt("level", 4);
                    a.putLong("timer", stopTimer(timeTaken));
                    intent.putExtras(a);
                    startActivity(intent);
                    finish();
                }
            }
        }, 500);
    }

    private long stopTimer(long timeTaken) {
        int seconds = 0;
        if (!notRunning) {
            timeTaken = chronometer.getBase() - SystemClock.elapsedRealtime();
            seconds = (int) timeTaken / 1000;
            chronometer.stop();
            notRunning =true;
            System.out.println(notRunning);
        }
//        return Math.abs(seconds);
        return timeTaken;
    }

    private void updateScore(int i) {
        scoreTV.setText(String.valueOf(i));
    }

    private void updateQuestions() {
        question.setImageResource(questionLibrary.getQuestions(questionID));
        choice1.setText(questionLibrary.getAnswer1(questionID));
        choice2.setText(questionLibrary.getAnswer2(questionID));
        choice3.setText(questionLibrary.getAnswer3(questionID));

        mAnswer = questionLibrary.getCorrectAnswer(questionID);
        questionID++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}