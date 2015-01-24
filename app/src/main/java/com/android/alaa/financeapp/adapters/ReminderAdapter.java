package com.android.alaa.financeapp.adapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.alaa.financeapp.database.ReminderDB;
import com.android.alaa.financeapp.models.Reminder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alaa on 1/20/2015.
 */
public class ReminderAdapter extends DBAdapter {
    private static ReminderAdapter mAdapter;

    private ReminderAdapter() {
    }

    public static ReminderAdapter getInstance() {
        if (mAdapter == null)
            mAdapter = new ReminderAdapter();

        return mAdapter;
    }

    public void addDBEntry(SQLiteDatabase database, Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(ReminderDB.NAME, reminder.getName());
        values.put(ReminderDB.DATE, reminder.getDate());
        values.put(ReminderDB.PLACE, reminder.getPlace());
        values.put(ReminderDB.DESCRIPTION, reminder.getDescription());
        values.put(ReminderDB.REPEAT, reminder.getRepeatRate());
        long insertId = database.insert(ReminderDB.TABLE_NAME, null,
                values);
    }

    public List<Reminder> queryDBEntry(SQLiteDatabase database) {
        List<Reminder> reminders = new ArrayList<Reminder>();

        Cursor cursor = database.query(ReminderDB.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Reminder reminder = cursorToReminder(cursor);
            reminders.add(reminder);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return reminders;
    }

    private Reminder cursorToReminder(Cursor cursor) {
        Reminder reminder = new Reminder(cursor.getString(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getInt(4));
        return reminder;
    }
}