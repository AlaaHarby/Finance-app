package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.BudgetDB;
import com.android.alaa.financeapp.database.CommentDB;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Budget;
import com.android.alaa.financeapp.models.Comment;

import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class CommentAdapter extends DBAdapter {
    private static CommentAdapter mAdapter;

    private CommentAdapter(){
    }

    public static CommentAdapter getInstance(){
        if(mAdapter == null)
            mAdapter = new CommentAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Comment comment) {
        ContentValues values = new ContentValues();
        values.put(CommentDB.COMMENT, comment.getComment());
        values.put(CommentDB.DATE, String.valueOf(comment.getDate()));
        long insertId = database.insert(CommentDB.TABLE_NAME, null,
                values);
    }

    public List<DBAdapter> queryDBEntry(DatabaseHelper helper) {
        return null;
    }
}