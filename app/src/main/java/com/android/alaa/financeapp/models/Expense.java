package com.android.alaa.financeapp.models;

import java.util.Date;

/**
 * Created by Alaa on 1/13/2015.
 */
public class Expense {

    double mAmount;
    Date mDate;
    String mPayee;
    String mCategory;
    String mNote;
    String mPayMethod;
    // adding account or receipt picture

    public Expense(double mAmount, Date mDate, String mCategory){
        this.mAmount = mAmount;
        this.mDate = mDate;
        this.mCategory = mCategory;
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
}
