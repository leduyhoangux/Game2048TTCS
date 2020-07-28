package com.example.game2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GAME2048.db";
    private static final String TABLE_SCORE = "SCORE";
    private static final String TABLE_BOX = "BOX";

    private static final  String SCORE = "SCORE";
    private static final String HIGH_SCORE = "HIGH_SCORE";
    private static final String COL_1 = "COL_1";
    private static final String COL_2 = "COL_2";
    private static final String COL_3 = "COL_3";
    private static final String COL_4 = "COL_4";

    private static final int VERSION = 1;

    private static final String SQL_CREATE_TABLE_SCORE = "CREATE TABLE IF NOT EXISTS " + TABLE_SCORE
            + "(" + SCORE + " INTEGER, " + HIGH_SCORE + " INTEGER)";
    private static final String SQL_DELETE_TABLE_SCORE = "DROP TABLE IF EXISTS " + TABLE_SCORE;

    private static final String SQL_CREATE_TABLE_BOX = "CREATE TABLE IF NOT EXISTS " + TABLE_BOX
            + "(" + COL_1 + " INTEGER, "
            + COL_2 + " INTEGER, "
            + COL_3 + " INTEGER, "
            + COL_4 + " INTEGER)";
    private static final String SQL_DELETE_TABLE_BOX = "DROP TABLE IF EXISTS " + TABLE_BOX;


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SCORE);
        db.execSQL(SQL_CREATE_TABLE_BOX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE_SCORE);
        db.execSQL(SQL_DELETE_TABLE_BOX);
        onCreate(db);
    }

    public void insertHighScore(int highScore){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuesScore = new ContentValues();

        contentValuesScore.put(HIGH_SCORE, highScore);

        db.insert(TABLE_SCORE, null, contentValuesScore);

        db.close();
    }

    public void insertData(int score, int highScore, int[][] box){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValuesScore = new ContentValues();
        ContentValues contentValuesBox = new ContentValues();

        contentValuesScore.put(SCORE, score);
        contentValuesScore.put(HIGH_SCORE, highScore);

        db.insert(TABLE_SCORE, null, contentValuesScore);

        for(int i = 0; i < 4; i++) {
            contentValuesBox.put(COL_1, box[i][0]);
            contentValuesBox.put(COL_2, box[i][1]);
            contentValuesBox.put(COL_3, box[i][2]);
            contentValuesBox.put(COL_4, box[i][3]);
            db.insert(TABLE_BOX, null, contentValuesBox);
        }

        db.close();
    }

    public int[] getScore(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SCORE, null);

        cursor.moveToLast();
        if(!cursor.moveToLast()) return null;

        int[] score = new int[2];
        score[0] = cursor.getInt(cursor.getColumnIndex(SCORE));
        score[1] = cursor.getInt(cursor.getColumnIndex(HIGH_SCORE));

        return score;
    }

    public int[][] getBox() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOX, null);
        cursor.moveToLast();
        if(!cursor.moveToLast()) return null;

        int[][] box = new int[4][4];
        int i = 3;
        while(i >= 0) {
            box[i][0] = cursor.getInt(cursor.getColumnIndex(COL_1));
            box[i][1] = cursor.getInt(cursor.getColumnIndex(COL_2));
            box[i][2] = cursor.getInt(cursor.getColumnIndex(COL_3));
            box[i][3] = cursor.getInt(cursor.getColumnIndex(COL_4));
            i--;

            cursor.moveToPrevious();
        }

        return box;
    }
}
