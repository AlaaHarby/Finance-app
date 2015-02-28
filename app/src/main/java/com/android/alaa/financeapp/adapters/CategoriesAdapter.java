package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.CategoryDB;
import com.android.alaa.financeapp.models.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class CategoriesAdapter extends DBAdapter {
    private static CategoriesAdapter mAdapter;

    private CategoriesAdapter() {
    }

    public static CategoriesAdapter getInstance() {
        if (mAdapter == null)
            mAdapter = new CategoriesAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoryDB.NAME, category.getName());
        long insertId = database.insert(CategoryDB.TABLE_NAME, null,
                values);
    }

    public List<Category> queryDBEntry(SQLiteDatabase database) {
        List<Category> categories = new ArrayList<Category>();

        Cursor cursor = database.query(CategoryDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return categories;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category(cursor.getString(0));
        return category;
    }
}