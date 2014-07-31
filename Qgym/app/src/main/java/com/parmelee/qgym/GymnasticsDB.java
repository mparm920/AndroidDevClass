package com.parmelee.qgym;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mparm920 on 7/26/14.
 */
public class GymnasticsDB extends SQLiteOpenHelper {
    private final String TAG = "Database";

    GymnasticsDB(Context context) {
        super(context, context.getString(R.string.db), null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;"); //turns on foreign key constraints
        Log.d(TAG, "foreign key set to on");
        db.execSQL(createGymnastTable()); //Creates table for gymnasts info
        Log.d(TAG, "Gymanst table created");
        db.execSQL(createMeetTable()); //creates table for meets and dates of meets
        Log.d(TAG, "Meet table created");
        db.execSQL(createConfigurationTable()); //creates table to store any data for config
        Log.d(TAG, "config table created");
        try {
            db.execSQL(createScoresTable()); //creates table to track scores
            Log.d(TAG, "Scores table created");
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {
        //never upgraded yet
    }

    private String createGymnastTable() {
        String table = "";

        table = "CREATE TABLE " + DB_Schema.Gymnast.TABLE_NAME +
                " ( " + DB_Schema.Gymnast._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_Schema.Gymnast.FIRST_NAME + " TEXT, " +
                DB_Schema.Gymnast.LAST_NAME + " TEXT, " +
                DB_Schema.Gymnast.PHONE + " TEXT, " +
                DB_Schema.Gymnast.EMAIL + " TEXT, " +
                DB_Schema.Gymnast.DIVISION + " INTEGER " +
                ");";

        Log.d(TAG, "Gymnast Table method");
        return table;
    }

    private String createMeetTable() {
        String table = "";

        table = "CREATE TABLE " + DB_Schema.Meet.TABLE_NAME +
                " ( " + DB_Schema.Meet._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_Schema.Meet.MEET_NAME + " TEXT, " +
                DB_Schema.Meet.DATE + " TEXT " +
                ");";
        Log.d(TAG, "Meet Table method");
        return table;
    }

    private String createScoresTable() {
        String table = "";

        table = "CREATE TABLE " + DB_Schema.Scores.TABLE_NAME +
                " ( " + DB_Schema.Scores._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_Schema.Scores.GYMNAST_ID + " INTEGER NOT NULL, " +
                DB_Schema.Scores.MEET_ID + " INTEGER NOT NULL, " +
                DB_Schema.Scores.CLASS + " TEXT, " +
                DB_Schema.Scores.VAULT + " REAL, " +
                DB_Schema.Scores.BARS + " REAL, " +
                DB_Schema.Scores.BEAM + " REAL, " +
                DB_Schema.Scores.FLOOR + " REAL, " +
                DB_Schema.Scores.ALL_AROUND + " REAL, " +
                DB_Schema.Scores.NOTES + " BLOB, " +
                "FOREIGN KEY(" + DB_Schema.Scores.GYMNAST_ID + ") REFERENCES " + DB_Schema.Gymnast.TABLE_NAME + "(" + DB_Schema.Gymnast._ID + "), " +
                "FOREIGN KEY(" + DB_Schema.Scores.MEET_ID + ") REFERENCES " + DB_Schema.Meet.TABLE_NAME + "(" + DB_Schema.Meet._ID + ") " +
                ");";

        Log.d(TAG, "Scores table method");
        return table;
    }

    private String createConfigurationTable() {
        String table = "";

        table = "CREATE TABLE " + DB_Schema.Configuration.TABLE_NAME +
                " ( " + DB_Schema.Configuration._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DB_Schema.Configuration.VAULT_QUAL + " REAL, " +
                DB_Schema.Configuration.BARS_QUAL + " REAL, " +
                DB_Schema.Configuration.BEAM_QUAL + " REAL, " +
                DB_Schema.Configuration.FLOOR_QUAL + " REAL, " +
                DB_Schema.Configuration.BACKGROUND_COLOR + " TEXT, " +
                DB_Schema.Configuration.ACCENT_COLOR + " TEXT " +
                ");";
        Log.d(TAG, "Config table Method");
        return table;
    }
}
