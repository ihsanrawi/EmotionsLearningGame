package com.example.emotionslearninggame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScore extends MainActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    TextView tv;

    private static final String DATABASE_NAME = "score";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        dbHelper =new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        tv = (TextView) findViewById(R.id.highScore_TV);

        highScore();
    }

    private void highScore() {
        StringBuilder sb = new StringBuilder();
        String[] scoreTable = new String[] {KEY_ID, KEY_SCORE, KEY_TIME};
        Cursor c = db.query(DATABASE_NAME, scoreTable, null, null, null, null, KEY_SCORE + " DESC");

        if (c.moveToFirst()) {
            int count = 1;
            do {
                String score = c.getString(1);
                String timer = c.getString(2);

                sb.append(count + " :\t " + score + "\t " + timer + " s");
                    sb.append("\n");
                ++count;
            }while (c.moveToNext());
        }
        tv.setText(sb.toString());
    }
}
