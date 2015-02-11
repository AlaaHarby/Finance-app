package com.android.alaa.financeapp.controllers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.adapters.BudgetAdapter;
import com.android.alaa.financeapp.adapters.CategoriesAdapter;
import com.android.alaa.financeapp.adapters.CommentAdapter;
import com.android.alaa.financeapp.adapters.ExpensesAdapter;
import com.android.alaa.financeapp.adapters.IncomeAdapter;
import com.android.alaa.financeapp.adapters.ReminderAdapter;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Budget;
import com.android.alaa.financeapp.models.Category;
import com.android.alaa.financeapp.models.Comment;
import com.android.alaa.financeapp.models.Expense;
import com.android.alaa.financeapp.models.Income;
import com.android.alaa.financeapp.models.Reminder;

import java.util.List;

/**
 * Created by Alaa on 1/21/2015.
 */
public class QueryController {

    BudgetAdapter mBudgetAdapter;
    CommentAdapter mCommentAdapter;
    ExpensesAdapter mExpensesAdapter;
    IncomeAdapter mIncomeAdapter;
    ReminderAdapter mReminderAdapter;
    DatabaseHelper mDBHelper;
    SQLiteDatabase mDatabase;
    CategoriesAdapter mCategoriesAdapter;

    public QueryController(Context context) {
        mBudgetAdapter = BudgetAdapter.getInstance();
        mCommentAdapter = CommentAdapter.getInstance();
        mExpensesAdapter = ExpensesAdapter.getInstance();
        mIncomeAdapter = IncomeAdapter.getInstance();
        mReminderAdapter = ReminderAdapter.getInstance();
        mCategoriesAdapter = CategoriesAdapter.getInstance();
        mDBHelper = new DatabaseHelper(context);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public List<Budget> getBudgets() {
        return mBudgetAdapter.queryDBEntry(mDatabase);
    }

    public List<Comment> getComments() {
        return mCommentAdapter.queryDBEntry(mDatabase);
    }

    public List<Expense> getExpenses() {
        return mExpensesAdapter.queryDBEntry(mDatabase);
    }

    public List<Income> getIncomes() {
        return mIncomeAdapter.queryDBEntry(mDatabase);
    }

    public List<Reminder> getReminders() {
        return mReminderAdapter.queryDBEntry(mDatabase);
    }

    public List<Category> getCategories() {
        return mCategoriesAdapter.queryDBEntry(mDatabase);
    }
}
