package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by mparm920 on 7/13/14.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    //@Override
    //public String toString() { return mTitle; }

    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public Date getDate() { return mDate; }
    public void setDate(Date mDate) { this.mDate = mDate; }
    public boolean isSolved() { return mSolved; }
    public void setSolved(boolean mSolved) { this.mSolved = mSolved; }

}
