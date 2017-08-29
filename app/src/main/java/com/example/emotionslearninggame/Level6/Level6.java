package com.example.emotionslearninggame.Level6;

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

import com.example.emotionslearninggame.Database.CapturedEmotion;
import com.example.emotionslearninggame.Database.SQLiteDatabaseHandler;
import com.example.emotionslearninggame.DetectorService;
import com.example.emotionslearninggame.MainActivity;
import com.example.emotionslearninggame.NextLevel;
import com.example.emotionslearninggame.R;

public class Level6 extends MainActivity implements View.OnClickListener {

    private Button choice1, choice2, choice3;
    private ImageView emotion_View;
    private TextView scoreTV;

    private int score= 0, counter = 0, correct;
    private String answer;
    private MediaPlayer mp;
    Questions6 questionsLib = new Questions6();

    private Chronometer chronometer;
    private long timeTaken = 0;
    private boolean running;

    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level6);

        initialize();
        setQuestion();

        db = new SQLiteDatabaseHandler(this);
    }

    private void initialize() {
        emotion_View = (ImageView)findViewById(R.id.emotionView);
        choice1 = (Button)findViewById(R.id.choice1_Button);
        choice2 = (Button)findViewById(R.id.choice2_Button);
        choice3 = (Button)findViewById(R.id.choice3_Button);
        scoreTV = (TextView)findViewById(R.id.score_TextView);

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);

        chronometer = (Chronometer)findViewById(R.id.time_Ch);
        startTimer(timeTaken);
    }

    private void startTimer(long elapsedTime) {
        chronometer.setBase(SystemClock.elapsedRealtime() + elapsedTime);
        chronometer.start();
        running = false;
        System.out.println(running);
    }

    private void setQuestion() {
        emotion_View.setImageResource(questionsLib.getQuestions(counter));
        choice1.setText(questionsLib.getOptions1(counter));
        choice2.setText(questionsLib.getOptions2(counter));
        choice3.setText(questionsLib.getOptions3(counter));
        answer = questionsLib.getCorrectAnswer(counter);
        counter++;

    }

    private void scoreboard(int i) {
        scoreTV.setText(String.valueOf(i));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.choice1_Button) :
                if (choice1.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score += 10;
                    Toast.makeText(this, "Brilliant! The answer is correct", Toast.LENGTH_SHORT).show();
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "Nice try. The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }
                break;
            case (R.id.choice2_Button) :
                if (choice2.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score += 10;
                    Toast.makeText(this, "Brilliant! The answer is correct", Toast.LENGTH_SHORT).show();
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "Nice try. The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }
                break;
            case (R.id.choice3_Button) :
                if (choice3.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score += 10;
                    Toast.makeText(this, "Brilliant! The answer is correct", Toast.LENGTH_SHORT).show();
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "Nice try. The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0){
                        score = 0;
                    }
                    scoreboard(score);
                    CapturedEmotion e = new CapturedEmotion("Q_7To9_A(6)", score , DetectorService.getanger(),
                            DetectorService.getsurprise(), DetectorService.getjoy(), DetectorService.getsad(),
                            DetectorService.getdisgust(), DetectorService.getfear() );
                    db.addCapturedEmotion(e);
                    getNextQuestion(counter);
                }
                break;
        }
    }

    private void getNextQuestion(final int i) {
        choice1.setEnabled(false);
        choice2.setEnabled(false);
        choice3.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (counter < 6) {
                    setQuestion();
                    choice1.setEnabled(true);
                    choice2.setEnabled(true);
                    choice3.setEnabled(true);
                }else {
                    // if over 5 do this
                    Intent intent = new Intent(Level6.this, NextLevel.class);
                    Bundle a = new Bundle();
                    a.putInt("score", score);
                    a.putInt("level", 6);
                    a.putLong("timer", stopTimer(timeTaken));
                    intent.putExtras(a);
                    startActivity(intent);
                    finish();
                }
            }
        },500);
    }

    private long stopTimer(long timeTaken) {
        int seconds = 0;
        if (!running) {
            timeTaken = chronometer.getBase() - SystemClock.elapsedRealtime();
            seconds = (int) timeTaken / 1000;
            chronometer.stop();
            running =true;
            System.out.println(running);
        }
        return timeTaken;
    }

    private void sound(int i) {
        //Get mp3 files from questionsLib
        int sfx = questionsLib.getanswerSFX(i);
        Boolean mpStatus;

        try {
            mpStatus = mp.isPlaying();
        } catch (Exception e) {
            mpStatus = false;
        }

        if (mpStatus) {
            //mpStatus is True, mp is stop and release
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
}
