package com.example.emotionslearninggame;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.emotionslearninggame.Level3.MemoryPuzzle;
import com.example.emotionslearninggame.Level5.Level5;
import com.example.emotionslearninggame.Level7.Level7;


public class NextLevel extends AppCompatActivity implements View.OnClickListener {

    private Button next_Level;
    private TextView score, textVew;
    private int crntScore;

    private Chronometer chronometer;

    private int level;
    private long timer;
    private int timeTaken;

//    private DBHelper dbHelper;
//    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level);

        score = (TextView)findViewById(R.id.score_TV);
        next_Level = (Button)findViewById(R.id.nextlvl_btn);
        textVew = (TextView)findViewById(R.id.textView);

        chronometer = (Chronometer)findViewById(R.id.time_Ch);

        next_Level.setOnClickListener(this);
        getCrntScore();

        if (level == 3 || level == 5) {
            textVew.setText("Game Over! \n\nFinal Score");
            next_Level.setText("Main Menu");
        }
    }

    private void getCrntScore() {
        Bundle getCrntScore = getIntent().getExtras();
        crntScore = getCrntScore.getInt("score");
        score.setText(String.valueOf(crntScore));

        level = getCrntScore.getInt("level");
        timer = getCrntScore.getLong("timer");
    }

    @Override
    public void onClick(View v) {
        if (level == 4) {
            Intent level5 = new Intent(NextLevel.this, Level5.class);
            Bundle b = new Bundle();
            b.putInt("score", crntScore);
            b.putLong("timer", timer);
            System.out.println("Current Time is " + timer);
            level5.putExtras(b);
            startActivity(level5);
            finish();
        }else if (level == 6) {
            Intent level7 = new Intent(NextLevel.this, Level7.class);
            Bundle b = new Bundle();
            b.putInt("score", crntScore);
            b.putLong("timer", timer);
            level7.putExtras(b);
            startActivity(level7);
            finish();
        }else if (level == 7) {
            Intent level3 = new Intent(NextLevel.this, MemoryPuzzle.class);
            Bundle b = new Bundle();
            b.putInt("score", crntScore);
            b.putLong("timer", timer);
            level3.putExtras(b);
            startActivity(level3);
            finish();
        }
    }
}