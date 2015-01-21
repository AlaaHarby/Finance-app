package com.android.alaa.financeapp.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.adapters.BudgetAdapter;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Budget;

import java.util.List;

/**
 * Created by Alaa on 1/21/2015.
 */
public class QueryController {
    BudgetAdapter mBudgetAdapter;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDatabase;

    public QueryController(Context context){
        mBudgetAdapter = BudgetAdapter.getInstance();
        mDBHelper = new DatabaseHelper(context);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public List<Budget> getBudgets(){
        return mBudgetAdapter.queryDBEntry(mDatabase);
    }

}
