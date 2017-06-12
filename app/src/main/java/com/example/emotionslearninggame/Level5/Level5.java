package com.example.emotionslearninggame.Level5;

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
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emotionslearninggame.EndLevel;
import com.example.emotionslearninggame.Level6.Questions6;
import com.example.emotionslearninggame.NextLevel;
import com.example.emotionslearninggame.R;


public class Level5 extends AppCompatActivity implements View.OnClickListener {
    Questions5 questionsLibrary = new Questions5();
    Questions6 questions6 = new Questions6();
    TextView scoreTV, questionTV;
    Button option1, option2, option3, option4;

    String correctAnswer;
    int score, questionID = 0, narration, correct, getScore;
    private MediaPlayer mp;

    private Chronometer chronometer;
    private long timeWhenStopped;
    private boolean notRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level5);

        initialSetup();
        getScore();
        updateQuestion();
    }

    private void initialSetup() {
        scoreTV = (TextView)findViewById(R.id.score_TextView);
        questionTV = (TextView)findViewById(R.id.question_TextView);

        option1 = (Button)findViewById(R.id.choice1_button);
        option2 = (Button)findViewById(R.id.choice2_button);
        option3 = (Button)findViewById(R.id.choice3_button);
        option4 = (Button)findViewById(R.id.choice4_button);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        questionID = 0;
    }

    private void getScore() {
        Bundle getLvl4Score = getIntent().getExtras();
        int getScore = getLvl4Score.getInt("score");
        long getTimer = getLvl4Score.getLong("timer");
        scoreTV.setText(String.valueOf(getScore));
        score = getScore;
        timeWhenStopped = getTimer;

        System.out.println("Current timer " + timeWhenStopped);

        startTimer(timeWhenStopped);
    }

    private void startTimer(long time) {
        long seconds = time;
        System.out.println("seconds " + seconds);
        chronometer = (Chronometer)findViewById(R.id.chronometer3);
        chronometer.setBase(SystemClock.elapsedRealtime() + seconds);
        chronometer.start();
        System.out.println("Base " + chronometer.getBase());
        notRunning = false;
        System.out.println(notRunning);
    }

    private void updateQuestion() {
        questionTV.setText(questionsLibrary.getQuestion(questionID));

        option1.setText(questionsLibrary.getOption1(questionID));
        option2.setText(questionsLibrary.getOption2(questionID));
        option3.setText(questionsLibrary.getOption3(questionID));
        option4.setText(questionsLibrary.getOption4(questionID));

        option1.setBackgroundResource(questionsLibrary.getBackground1(questionID));
        option2.setBackgroundResource(questionsLibrary.getBackground2(questionID));
        option3.setBackgroundResource(questionsLibrary.getBackground3(questionID));
        option4.setBackgroundResource(questionsLibrary.getBackground4(questionID));

        correctAnswer = questionsLibrary.getCorrectAnswer(questionID);
        narration = questionsLibrary.getQNarrate(questionID);

        playNarrate();
        questionID++;
    }

    private void playNarrate() {
        Boolean mpStatus;

        try {
            mpStatus = mp.isPlaying();
        } catch (Exception e) {
            mpStatus = false;
        }

        if (mpStatus == true) {
            mp.stop();
            mp.reset();     //mp.reset() not mp.release() coz release buang terus while reset, reset the state
        }else {
            try {
                mp = new MediaPlayer();
                mp = MediaPlayer.create(getApplicationContext(), narration);
                mp.start();
            } catch (Exception e) {
                Log.i("Error playing sound", e.toString());
            }
        }
    }

    private void updateScore(int score) {
        scoreTV.setText(String.valueOf(score));
    }

    private void nextQuestion(final int questionID) {
        //disable the answer buttons
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (questionID < 8) {
                    updateQuestion();
                    //Re-enable the buttons
                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                }else {
                    Intent intent = new Intent(Level5.this, EndLevel.class);
                    Bundle a = new Bundle();
                    a.putInt("score", score);
                    a.putInt("timer", stopTimer(timeWhenStopped));
                    intent.putExtras(a);
                    startActivity(intent);
                    finish();
                }
            }
        }, 500);
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
//        return timeTaken;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.choice1_button):
                if (option1.getText().equals(correctAnswer)) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    nextQuestion(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(questionID);
                }
                break;
            case (R.id.choice2_button):
                if (option2.getText().equals(correctAnswer)) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    nextQuestion(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(questionID);
                }
                break;
            case (R.id.choice3_button):
                if (option3.getText().equals(correctAnswer)){
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    nextQuestion(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(questionID);
                }
                break;
            case (R.id.choice4_button):
                if (option4.getText().equals(correctAnswer)) {
                    correct = 1;
                    sfx(correct);
                    toast(correct);

                    score = score + 10;
                    updateScore(score);
                    nextQuestion(questionID);
                }else {
                    correct = 0;
                    sfx(correct);
                    toast(correct);

                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(questionID);
                }
                break;
        }
    }

    private void toast(int i) {
        if (i == 0) {
            final Toast toastIncorrect = Toast.makeText(this, "The correct answer is "+correctAnswer, Toast.LENGTH_SHORT);
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
}
