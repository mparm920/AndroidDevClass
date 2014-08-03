package com.parmelee.qgym;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by mparm920 on 7/27/14.
 */
public class DataAccessor {

    private static DataAccessor instance;
    private static SQLiteDatabase db;
    private static DatabaseUtils.InsertHelper ih;
    private static SQLiteStatement ScoreInsert;
    private static SQLiteStatement ScoreUpdate;

    public static DataAccessor getInstance(Context c) {
        if (instance == null) {
            instance = new DataAccessor();

            db = new GymnasticsDB(c).getWritableDatabase();
            compileSqlStatements();
        }
        return instance;
    }

    public Cursor getGymnast() {
        Cursor c = db.rawQuery("SELECT _id, (g.Last_Name + g.First_Name) as FullName, s.Vault, s.Bars, s.Beam, s.Floor\n" +
                "FROM tScores AS s\n" +
                "JOIN tGymnast AS g ON g._id = s.Gymnast_id;", null);

        return c;
    }

    public void setGymnastScores(String gymnastClass, Float vault, Float bars, Float beam, Float floor, int gymnastId, int meetId) {
        String checkForGymnast = "SELECT Gymnast_Id, Meet_Id FROM tScores WHERE Gymnast_Id = " +
                gymnastId + " AND Meet_Id = " + meetId;

        if (db.rawQuery(checkForGymnast, null).moveToFirst()) {
            ScoreUpdate.bindDouble(1, vault);
            ScoreUpdate.bindDouble(2, bars);
            ScoreUpdate.bindDouble(3, beam);
            ScoreUpdate.bindDouble(4, floor);
            ScoreUpdate.bindLong(5, gymnastId);
            ScoreUpdate.bindLong(6, meetId);
            ScoreUpdate.execute();
        } else {
            ScoreInsert.bindLong(1, gymnastId);
            ScoreInsert.bindLong(2, meetId);
            ScoreInsert.bindString(3, gymnastClass);
            ScoreInsert.bindDouble(4, vault);
            ScoreInsert.bindDouble(5, bars);
            ScoreInsert.bindDouble(6, beam);
            ScoreInsert.bindDouble(7, floor);
            ScoreInsert.execute();
        }

    }

    public Cursor getMeets() {
        Cursor meet = db.rawQuery("SELECT * From " + DB_Schema.Meet.TABLE_NAME, null);
        return meet;
    }

    private static void compileSqlStatements() {
        ScoreInsert = db.compileStatement("INSERT INTO " + DB_Schema.Scores.TABLE_NAME +
                " (" + DB_Schema.Scores.GYMNAST_ID + ", " + DB_Schema.Scores.MEET_ID + ", " + DB_Schema.Scores.CLASS +
                ", " + DB_Schema.Scores.VAULT + ", " + DB_Schema.Scores.BARS + ", " + DB_Schema.Scores.BEAM + ", " +
                DB_Schema.Scores.FLOOR + ") VALUES (?, ?, ?, ?, ?, ?, ?);");

        ScoreUpdate = db.compileStatement("UPDATE " + DB_Schema.Scores.TABLE_NAME + " SET " +
                DB_Schema.Scores.VAULT + " = ?, " + DB_Schema.Scores.BARS + " = ?, " + DB_Schema.Scores.BEAM +
        " = ?, " + DB_Schema.Scores.FLOOR + " = ? WHERE " + DB_Schema.Scores.GYMNAST_ID + " = ? AND " +
                DB_Schema.Scores.MEET_ID + " = ?;");
    }

}
