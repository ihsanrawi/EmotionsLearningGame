package com.example.emotionslearninggame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScore extends AppCompatActivity {

    DBHelper dbHelper;
    SQLiteDatabase db;
    TextView tv;

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
        Cursor c = db.query("highScore", new String[] {"id", "score"}, null, null, null, null, null);

        if (c.moveToFirst()) {
            do {
                int id = c.getInt(0);
                String score = c.getString(1);

                sb.append("" + id + ": " + score);
                sb.append("\n");
            }while (c.moveToNext());
        }
        tv.setText(sb.toString());
    }
}
