package com.example.emotionslearninggame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.emotionslearninggame.Flashcard.HowYouFeel;

import static com.example.emotionslearninggame.R.id.toolbar;

public class EndLevel extends MainActivity {
    private Toolbar toolbar;
    private TextView finalScore, finalTimer;
    private int score,timer;

    private DBHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_level);

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        toolbar = (Toolbar)findViewById(R.id.toolbar3);
        finalScore = (TextView)findViewById(R.id.score_TV);
        finalTimer = (TextView)findViewById(R.id.timer_TV);

        getScoreAndTimer();
        loadToolbar();
    }

    private void getScoreAndTimer() {
        Bundle getFinalScoreTimer = getIntent().getExtras();
        score = getFinalScoreTimer.getInt("score");
        finalScore.setText(String.valueOf(score));

        timer = getFinalScoreTimer.getInt("timer");
        finalTimer.setText(String.valueOf(timer) + " S");
    }

    private void loadToolbar() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.home);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO score (score, time) " + "VALUES " + "(" + score + ", " + timer + ");");

                Intent mainActivity = new Intent(EndLevel.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }
}
