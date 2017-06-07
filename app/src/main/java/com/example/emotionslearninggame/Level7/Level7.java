package com.example.emotionslearninggame.Level7;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emotionslearninggame.Level6.Questions6;
import com.example.emotionslearninggame.NextLevel;
import com.example.emotionslearninggame.R;

public class Level7 extends AppCompatActivity implements View.OnClickListener {

    Questions7 questions = new Questions7();
    Questions6 questionsLib = new Questions6();

    private Button option1, option2, option3;
    private ImageView situation;
    private TextView scoreTv;

    private String answer;
    private int score, counter,getScore, correct;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level7);

        initialize();
        getQuestions();
    }

    private void initialize() {
        situation = (ImageView)findViewById(R.id.situation_View);
        option1 = (Button)findViewById(R.id.choice1_Button);
        option2 = (Button)findViewById(R.id.choice2_Button);
        option3 = (Button)findViewById(R.id.choice3_Button);
        scoreTv = (TextView)findViewById(R.id.score_TextView);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        counter = 0;

        Bundle getCrntScore = getIntent().getExtras();
        getScore = getCrntScore.getInt("score");
        score = getScore;
        scoreTv.setText(String.valueOf(score));
    }

    private void getQuestions() {
        situation.setImageResource(questions.getSituation(counter));
        option1.setText(questions.getOption1(counter));
        option2.setText(questions.getOption2(counter));
        option3.setText(questions.getOption3(counter));

        answer = questions.getAnswer(counter);
        counter++;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.choice1_Button):
                if (option1.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score = score + 10;
                    Toast.makeText(this, "The answer is correct!", Toast.LENGTH_SHORT).show();
                    updateScore(score);
                    nextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(counter);
                }
                break;
            case (R.id.choice2_Button):
                if (option2.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score = score + 10;
                    Toast.makeText(this, "The answer is correct!", Toast.LENGTH_SHORT).show();
                    updateScore(score);
                    nextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(counter);
                }
                break;
            case (R.id.choice3_Button):
                if (option3.getText().equals(answer)) {
                    correct = 1;
                    sound(correct);
                    score = score + 10;
                    Toast.makeText(this, "The answer is correct!", Toast.LENGTH_SHORT).show();
                    updateScore(score);
                    nextQuestion(counter);
                }else {
                    correct = 0;
                    sound(correct);
                    Toast.makeText(this, "The correct answer is "+answer, Toast.LENGTH_SHORT).show();
                    if (score > 0) {
                        score = score - 3;
                    }
                    if (score < 0) {
                        score = 0;
                    }
                    updateScore(score);
                    nextQuestion(counter);
                }
                break;
        }
    }

    private void sound(int i) {
        int sfx = questionsLib.getanswerSFX(i);
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

    private void updateScore(int score) {
        scoreTv.setText(String.valueOf(score));
    }

    private void nextQuestion(final int questionID) {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (counter < 5) {
                    getQuestions();
                    option1.setEnabled(true);
                    option2.setEnabled(true);
                    option3.setEnabled(true);
                }else {
                    // if over 5 do this
                    Intent intent = new Intent(Level7.this, NextLevel.class);
                    Bundle a = new Bundle();
                    a.putInt("score", score);
                    a.putInt("level", 7);
                    intent.putExtras(a);
                    startActivity(intent);
                    finish();
                }
            }
        }, 500);
    }

}
