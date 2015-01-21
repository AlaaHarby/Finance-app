package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.CommentDB;
import com.android.alaa.financeapp.database.DatabaseHelper;
import com.android.alaa.financeapp.models.Comment;

import java.util.ArrayList;
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
        values.put(CommentDB.DATE, comment.getDate());
        long insertId = database.insert(CommentDB.TABLE_NAME, null,
                values);
    }

    public List<Comment> queryDBEntry(SQLiteDatabase database) {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(CommentDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment(cursor.getString(0), cursor.getString(1));
        return comment;
    }}