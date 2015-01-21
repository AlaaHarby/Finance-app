package com.android.alaa.financeapp.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.adapters.BudgetAdapter;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Budget;

/**
 * Created by Alaa on 1/21/2015.
 */
public class InputController {

    BudgetAdapter mBudgetAdapter;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDatabase;

    public InputController(Context context){
        mBudgetAdapter = BudgetAdapter.getInstance();
        mDBHelper = new DatabaseHelper(context);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void insertNewBudget(Budget budget){
        mBudgetAdapter.addDBEntry(mDatabase,budget);
    }

}
