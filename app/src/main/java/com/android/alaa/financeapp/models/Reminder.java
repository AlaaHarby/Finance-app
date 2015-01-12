package com.android.alaa.financeapp.models;

import java.util.Date;

/**
 * Created by Alaa on 1/12/2015.
 * Reminder class holds events added to calender with their properties.
 */
public class Reminder {
    String mName;
    String mDescription;
    String mPlace;
    boolean mRepeat;
    int mRepeatRate;
    Date mDate;

    public Reminder(String mName){
        this.mName = mName;
    }

    public Reminder(String mName,
                    String mDescription,
                    String mPlace,
                    boolean mRepeat,
                    int mRepeatRate,
                    Date mDate) {

        this.mName = mName;
        this.mDescription = mDescription;
        this.mPlace = mPlace;
        this.mRepeat = mRepeat;
        this.mRepeatRate = mRepeatRate;
        this.mDate = mDate;

    }

    public String getName() {
        return mName;
    }

    public Date getDate() {
        return mDate;
    }

    public int getRepeatRate() {
        return mRepeatRate;
    }

    public boolean isRepeat() {
        return mRepeat;
    }

    public String getPlace() {
        return mPlace;
    }

    public String getDescription() {
        return mDescription;
    }
}
