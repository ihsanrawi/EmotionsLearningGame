package com.example.emotionslearninggame.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CapturedEmotionsDB";
    private static final String TABLE_NAME = "CapturedEmotions";
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SCORE = "score";
    private static final String KEY_ANGER = "anger";
    private static final String KEY_SURPRISE = "surprise";
    private static final String KEY_JOY = "joy";
    private static final String KEY_SAD = "sad";
    private static final String KEY_DISGUST = "disgust";
    private static final String KEY_FEAR = "fear";
    private static final String[] COLUMNS = { KEY_ID, KEY_TYPE, KEY_SCORE,
            KEY_ANGER, KEY_SURPRISE, KEY_JOY, KEY_SAD, KEY_DISGUST, KEY_FEAR };

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE CapturedEmotions ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "type TEXT, " + "score INTEGER, "
                + "anger REAL, " + "surprise REAL, " + "joy REAL, " + "sad REAL, " + "disgust REAL, "
                + "fear REAL )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(CapturedEmotion capturedEmotion ) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(capturedEmotion.getId()) });
        db.close();

    }

    public CapturedEmotion getCapturedEmotion(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        CapturedEmotion capturedEmotion = new CapturedEmotion();
        capturedEmotion.setId(Integer.parseInt(cursor.getString(0)));
        capturedEmotion.setType(cursor.getString(1));
        capturedEmotion.setScore(Integer.parseInt(cursor.getString(2)));
        capturedEmotion.setAnger(Float.valueOf(cursor.getString(3)));
        capturedEmotion.setSurprise(Float.valueOf(cursor.getString(4)));
        capturedEmotion.setJoy(Float.valueOf(cursor.getString(5)));
        capturedEmotion.setSad(Float.valueOf(cursor.getString(6)));
        capturedEmotion.setDisgust(Float.valueOf(cursor.getString(7)));
        capturedEmotion.setFear(Float.valueOf(cursor.getString(8)));

        return capturedEmotion;
    }

    public List<CapturedEmotion> allCapturedEmotions() {

        List<CapturedEmotion> capturedEmotions = new LinkedList<CapturedEmotion>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        CapturedEmotion capturedEmotion = null;

        if (cursor.moveToFirst()) {
            do {
                capturedEmotion = new CapturedEmotion();
                capturedEmotion.setId(Integer.parseInt(cursor.getString(0)));
                capturedEmotion.setType(cursor.getString(1));
                capturedEmotion.setScore(Integer.parseInt(cursor.getString(2)));
                capturedEmotion.setAnger(Float.valueOf(cursor.getString(3)));
                capturedEmotion.setSurprise(Float.valueOf(cursor.getString(4)));
                capturedEmotion.setJoy(Float.valueOf(cursor.getString(5)));
                capturedEmotion.setSad(Float.valueOf(cursor.getString(6)));
                capturedEmotion.setDisgust(Float.valueOf(cursor.getString(7)));
                capturedEmotion.setFear(Float.valueOf(cursor.getString(8)));
                capturedEmotions.add(capturedEmotion);
            } while (cursor.moveToNext());
        }

        return capturedEmotions;
    }

    public void addCapturedEmotion (CapturedEmotion capturedEmotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, capturedEmotion.getType());
        values.put(KEY_SCORE, capturedEmotion.getScore());
        values.put(KEY_ANGER, capturedEmotion.getAnger());
        values.put(KEY_SURPRISE, capturedEmotion.getSurprise());
        values.put(KEY_SAD, capturedEmotion.getSad());
        values.put(KEY_JOY, capturedEmotion.getJoy());
        values.put(KEY_DISGUST, capturedEmotion.getDisgust());
        values.put(KEY_FEAR, capturedEmotion.getFear());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updateCapturedEmotion (CapturedEmotion capturedEmotion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, capturedEmotion.getType());
        values.put(KEY_SCORE, capturedEmotion.getScore());
        values.put(KEY_ANGER, capturedEmotion.getAnger());
        values.put(KEY_SURPRISE, capturedEmotion.getSurprise());
        values.put(KEY_SAD, capturedEmotion.getSad());
        values.put(KEY_JOY, capturedEmotion.getJoy());
        values.put(KEY_DISGUST, capturedEmotion.getDisgust());
        values.put(KEY_FEAR, capturedEmotion.getFear());


        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(capturedEmotion.getId()) });

        db.close();

        return i;
    }

}