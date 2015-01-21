package com.android.alaa.financeapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.alaa.financeapp.models.Reminder;

/**
 * Created by Alaa on 1/14/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "finance.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        BudgetDB.onCreate(db);
        CommentDB.onCreate(db);
        ExpenseDB.onCreate(db);
        IncomeDB.onCreate(db);
        ReminderDB.onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        if (!db.isReadOnly())
            db.execSQL("PRAGMA foreign_keys=ON;");
        super.onOpen(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
