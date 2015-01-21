package com.android.alaa.financeapp.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.adapters.BudgetAdapter;
import com.android.alaa.financeapp.adapters.CommentAdapter;
import com.android.alaa.financeapp.adapters.ExpensesAdapter;
import com.android.alaa.financeapp.adapters.IncomeAdapter;
import com.android.alaa.financeapp.adapters.ReminderAdapter;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Budget;
import com.android.alaa.financeapp.models.Comment;
import com.android.alaa.financeapp.models.Expense;
import com.android.alaa.financeapp.models.Income;
import com.android.alaa.financeapp.models.Reminder;

/**
 * Created by Alaa on 1/21/2015.
 */
public class InputController {

    BudgetAdapter mBudgetAdapter;
    CommentAdapter mCommentAdapter;
    ExpensesAdapter mExpensesAdapter;
    IncomeAdapter mIncomeAdapter;
    ReminderAdapter mReminderAdapter;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDatabase;

    public InputController(Context context){
        mBudgetAdapter = BudgetAdapter.getInstance();
        mCommentAdapter = CommentAdapter.getInstance();
        mExpensesAdapter = ExpensesAdapter.getInstance();
        mIncomeAdapter = IncomeAdapter.getInstance();
        mReminderAdapter = ReminderAdapter.getInstance();
        mDBHelper = new DatabaseHelper(context);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void insertNewBudget(Budget budget){
        mBudgetAdapter.addDBEntry(mDatabase,budget);
    }

    public void insertNewComment(Comment comment){
        mCommentAdapter.addDBEntry(mDatabase,comment);
    }

    public void insertNewExpense(Expense expense){
        mExpensesAdapter.addDBEntry(mDatabase,expense);
    }

    public void insertNewIncome(Income income){
        mIncomeAdapter.addDBEntry(mDatabase,income);
    }

    public void insertNewReminder(Reminder reminder){
        mReminderAdapter.addDBEntry(mDatabase,reminder);
    }







}
