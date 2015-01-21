package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.alaa.financeapp.database.BudgetDB;
import com.android.alaa.financeapp.models.Budget;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class BudgetAdapter extends DBAdapter {

    private static BudgetAdapter mAdapter;

    private BudgetAdapter(){
    }

    public static BudgetAdapter getInstance(){
        if(mAdapter == null)
            mAdapter = new BudgetAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Budget budget) {
        ContentValues values = new ContentValues();
        values.put(BudgetDB.AMOUNT, budget.getAmount());
        values.put(BudgetDB.CATEGORY, budget.getCategory());
        long insertId = database.insert(BudgetDB.TABLE_NAME, null,
                values);
    }

    public List<Budget> queryDBEntry(SQLiteDatabase database) {
        List<Budget> budgets = new ArrayList<Budget>();

        Cursor cursor = database.query(BudgetDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Budget budget = cursorToBudget(cursor);
            budgets.add(budget);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return budgets;
    }

    private Budget cursorToBudget(Cursor cursor) {
        Budget budget = new Budget(cursor.getDouble(0), cursor.getString(1));
        return budget;
    }
}
