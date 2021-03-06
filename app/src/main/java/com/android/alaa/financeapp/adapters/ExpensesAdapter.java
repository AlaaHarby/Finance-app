package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.ExpenseDB;
import com.android.alaa.financeapp.models.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class ExpensesAdapter extends DBAdapter {
    private static ExpensesAdapter mAdapter;

    private ExpensesAdapter() {
    }

    public static ExpensesAdapter getInstance() {
        if (mAdapter == null)
            mAdapter = new ExpensesAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Expense expense) {
        ContentValues values = new ContentValues();
        values.put(ExpenseDB.AMOUNT, expense.getAmount());
        values.put(ExpenseDB.DATE, expense.getDate());
        values.put(ExpenseDB.CATEGORY, expense.getCategory());
        values.put(ExpenseDB.PAYEE, expense.getPayee());
        values.put(ExpenseDB.NOTE, expense.getNote());
        values.put(ExpenseDB.METHOD, expense.getPayMethod());
        long insertId = database.insert(ExpenseDB.TABLE_NAME, null,
                values);
    }

    public void removeDBEntry(SQLiteDatabase database, Expense expense) {
        database.delete(ExpenseDB.TABLE_NAME, ExpenseDB._ID + "=" + expense.getID(), null);
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
        Expense expense = new Expense(cursor.getInt(0), cursor.getDouble(1), cursor.getLong(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return expense;
    }

    public void updateDBEntry(SQLiteDatabase database, Expense expense) {
        ContentValues values = new ContentValues();
        values.put(ExpenseDB.AMOUNT, expense.getAmount());
        values.put(ExpenseDB.DATE, expense.getDate());
        values.put(ExpenseDB.CATEGORY, expense.getCategory());
        values.put(ExpenseDB.PAYEE, expense.getPayee());
        values.put(ExpenseDB.NOTE, expense.getNote());
        values.put(ExpenseDB.METHOD, expense.getPayMethod());
        database.update(ExpenseDB.TABLE_NAME, values, ExpenseDB._ID + "=" + expense.getID(), null);
    }
}