package com.android.alaa.financeapp.models;

/**
 * Created by Alaa on 1/12/2015.
 */
public class Budget {

    double mAmount;
    String mCategory;
    String mDescription;

    public Budget(double mAmount, String mCategory){
        this.mAmount = mAmount;
        this.mCategory = mCategory;
    }

    public String getCategory() {
        return mCategory;
    }

    public double getAmount() {
        return mAmount;
    }
}
