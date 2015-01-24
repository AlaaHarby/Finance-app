package com.android.alaa.financeapp.models;

/**
 * Created by Alaa on 1/11/2015.
 */
public class Income {

    String mSource;
    double mAmount;

    public Income(double mAmount, String mSource){

        // Add Deprecation to it later
        this.mSource = mSource;
        this.mAmount = mAmount;
    }

    public String getSource() {
        return mSource;
    }

    public double getAmount() {
        return mAmount;
    }
}
