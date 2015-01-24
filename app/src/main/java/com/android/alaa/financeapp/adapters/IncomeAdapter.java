package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.android.alaa.financeapp.database.IncomeDB;
import com.android.alaa.financeapp.models.Income;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class IncomeAdapter extends DBAdapter {
    private static IncomeAdapter mAdapter;

    private IncomeAdapter(){
    }

    public static IncomeAdapter getInstance(){
        if(mAdapter == null)
            mAdapter = new IncomeAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Income income) {
        ContentValues values = new ContentValues();
        values.put(IncomeDB.AMOUNT, income.getAmount());
        values.put(IncomeDB.SOURCE, income.getSource());
        long insertId = database.insert(IncomeDB.TABLE_NAME, null,
                values);
    }

    public List<Income> queryDBEntry(SQLiteDatabase database) {
        List<Income> incomes = new ArrayList<Income>();

        Cursor cursor = database.query(IncomeDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Income income = cursorToIncome(cursor);
            incomes.add(income);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return incomes;
    }

    private Income cursorToIncome(Cursor cursor) {
        Income income = new Income(cursor.getDouble(0), cursor.getString(1));
        return income;
    }

}
