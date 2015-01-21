package com.android.alaa.financeapp.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alaa on 1/14/2015.
 */
public class ExpenseDB {
    public static final String TABLE_NAME = "expense";
    public static final String AMOUNT = "amount";
    public static final String CATEGORY = "category";
    public static final String DATE = "date";
    public static final String PAYEE = "payee";
    public static final String NOTE = "note";
    public static final String METHOD = "method";

    // Database creation sql statement
    private static final String DATABASE_CREATE;

    static {
        DATABASE_CREATE = "CREATE TABLE "
                + TABLE_NAME
                + "("
                + AMOUNT + " REAL NOT NULL, "
                + DATE + " TEXT NOT NULL, "
                + CATEGORY + " TEXT NOT NULL, "
                + PAYEE + " TEXT, "
                + NOTE + " TEXT, "
                + METHOD + " TEXT"
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
