package com.android.alaa.financeapp.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alaa on 1/14/2015.
 */
public class IncomeDB {
    public static final String TABLE_NAME = "income";
    public static final String AMOUNT = "amount";
    public static final String SOURCE = "source";

    // Database creation sql statement
    private static final String DATABASE_CREATE;

    static {
        DATABASE_CREATE = "CREATE TABLE "
                + TABLE_NAME
                + "("
                + AMOUNT + " REAL NOT NULL, "
                + SOURCE + " TEXT"
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
