package com.android.alaa.financeapp.models;

/**
 * Created by Alaa on 1/12/2015.
 * Reminder class holds events added to calender with their properties.
 */
public class Reminder {
    String mName;
    String mDescription;
    String mPlace;
    int mRepeatRate;
    String mDate;

    public Reminder(String mName){
        this.mName = mName;
    }

    public Reminder(String mName,
                    String mDescription,
                    String mPlace,
                    int mRepeatRate,
                    String mDate) {

        this.mName = mName;
        this.mDescription = mDescription;
        this.mPlace = mPlace;
        this.mRepeatRate = mRepeatRate;
        this.mDate = mDate;

    }

    public String getName() {
        return mName;
    }

    public String getDate() {
        return mDate;
    }

    public int getRepeatRate() {
        return mRepeatRate;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDescription() {
        return mDescription;
    }
}
