package com.android.alaa.financeapp.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alaa on 1/14/2015.
 */
public class CommentDB {

    public static final String TABLE_NAME = "comment";
    public static final String COMMENT = "comment";
    public static final String DATE = "date";

    // Database creation sql statement
    private static final String DATABASE_CREATE;

    static {
        DATABASE_CREATE = "CREATE TABLE "
                + TABLE_NAME
                + "("
                + COMMENT + " TEXT, "
                + DATE + " TEXT"
                + ");";
    }

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
