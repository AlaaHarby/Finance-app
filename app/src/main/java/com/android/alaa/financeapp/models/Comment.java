package com.android.alaa.financeapp.models;

import java.util.Date;

/**
 * Created by Alaa on 1/11/2015.
 * This class represents Comment feature attributes, used in calendar to leave comments on specific days.
 */
public class Comment {
    String mComment;
    Date mDate;

    public Comment(String mComment, Date mDate){
        this.mComment = mComment;
        this.mDate = mDate;
    }

    public String getComment(){
        return mComment;
    }

    public Date mDate(){
        return mDate;
    }

}
