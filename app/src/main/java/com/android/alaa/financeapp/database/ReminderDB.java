package com.android.alaa.financeapp.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alaa on 1/14/2015.
 */
public class ReminderDB {
    public static final String TABLE_NAME = "reminder";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PLACE = "place";
    public static final String DATE = "date";
    public static final String REPEAT = "repeat";

    // Database creation sql statement
    private static final String DATABASE_CREATE;

    static {
        DATABASE_CREATE = "CREATE TABLE "
                + TABLE_NAME
                + "("
                + NAME + " TEXT NOT NULL, "
                + DATE + " INTEGER NOT NULL, "
                + PLACE + " TEXT, "
                + DESCRIPTION + " TEXT, "
                + REPEAT + " INT "
                + ");";
    }

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
