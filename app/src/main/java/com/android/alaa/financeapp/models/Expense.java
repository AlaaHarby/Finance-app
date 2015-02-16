package com.android.alaa.financeapp.models;

import java.util.Date;

/**
 * Created by Alaa on 1/13/2015.
 */
public class Expense {

    int mID;
    double mAmount;
    long mDate;
    String mPayee;
    String mCategory;
    String mNote;
    String mPayMethod;
    // adding account or receipt picture

    public Expense() {

    }

    public Expense(double mAmount, long mDate, String mCategory, String mPayee, String mNote, String mPayMethod) {
        this(0, mAmount, mDate, mCategory, mPayee, mNote, mPayMethod);
    }

    public Expense(int mID, double mAmount, long mDate, String mCategory, String mPayee, String mNote, String mPayMethod) {
        this.mID = mID;
        this.mAmount = mAmount;
        this.mDate = mDate;
        this.mCategory = mCategory;
        this.mPayee = mPayee;
        this.mNote = mNote;
        this.mPayMethod = mPayMethod;
    }

    public int getID() {
        return mID;
    }

    public String getPayee() {
        return mPayee;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getPayMethod() {
        return mPayMethod;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public long getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date.getTime();
    }

    public String getCategory() {
        return mCategory;
    }

    public String toString() {
        return String.format("[Expense] %s %d %f\n", mNote, mDate, mAmount);
    }
}
