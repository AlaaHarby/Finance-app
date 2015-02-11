package com.android.alaa.financeapp.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alaa on 1/14/2015.
 */
public class CategoryDB {
    public static final String TABLE_NAME = "category";
    public static final String NAME = "name";
    public static final String[] INITIAL_CATEGORIES = new String[]{"Food", "Entertainment", "House", "Transportation", "Clothes"};

    // Database creation sql statement
    private static final String DATABASE_CREATE;

    static {
        DATABASE_CREATE = "CREATE TABLE "
                + TABLE_NAME
                + "("
                + NAME + " TEXT NOT NULL "
                + ");";
    }

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        for(String name : INITIAL_CATEGORIES) {
            initialInsert(database, name);
        }
    }

    private static void initialInsert(SQLiteDatabase database, String name) {
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        long insertId = database.insert(TABLE_NAME, null,
                values);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
