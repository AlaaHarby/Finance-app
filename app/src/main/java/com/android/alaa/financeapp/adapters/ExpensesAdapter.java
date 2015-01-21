package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.database.ExpenseDB;
import com.android.alaa.financeapp.models.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class ExpensesAdapter extends DBAdapter {
    private static ExpensesAdapter mAdapter;

    private ExpensesAdapter(){
    }

    public static ExpensesAdapter getInstance(){
        if(mAdapter == null)
            mAdapter = new ExpensesAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Expense expense) {
        ContentValues values = new ContentValues();
        values.put(ExpenseDB.AMOUNT, expense.getAmount());
        values.put(ExpenseDB.CATEGORY, expense.getCategory());
        values.put(ExpenseDB.DATE, expense.getDate());
        values.put(ExpenseDB.METHOD, expense.getPayMethod());
        values.put(ExpenseDB.NOTE, expense.getNote());
        values.put(ExpenseDB.PAYEE, expense.getPayee());
        long insertId = database.insert(ExpenseDB.TABLE_NAME, null,
                values);
    }

    public List<Expense> queryDBEntry(SQLiteDatabase database) {
        List<Expense> expenses = new ArrayList<Expense>();

        Cursor cursor = database.query(ExpenseDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Expense expense = cursorToExpense(cursor);
            expenses.add(expense);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return expenses;
    }

    private Expense cursorToExpense(Cursor cursor) {
        Expense expense = new Expense(cursor.getDouble(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3),cursor.getString(4),cursor.getString(5));
        return expense;
    }
}