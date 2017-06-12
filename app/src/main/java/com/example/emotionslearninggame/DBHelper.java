package com.example.emotionslearninggame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "highscore";
    private static final String TABLE_SCORE = "score";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SCORE + " int,"
                + KEY_TIME + " int" +
                ")";

        db.execSQL(CREATE_SCORE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS highScore;");
        onCreate(db);
    }
}
