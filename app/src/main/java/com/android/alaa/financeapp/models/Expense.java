package com.android.alaa.financeapp.models;

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

    public String getPayMethod() {
        return mPayMethod;
    }

    public double getAmount() {
        return mAmount;
    }

    public long getDate() {
        return mDate;
    }

    public String getCategory() {
        return mCategory;
    }
}
