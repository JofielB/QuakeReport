package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private String mMagnitude;
    private String mCity;
    private long mTimeDate;
    private Date mDate;

    public Earthquake(String magnitude, String city, long timeDate){
        mMagnitude = magnitude;
        mCity = city;
        mTimeDate = timeDate;
        mDate = new Date(mTimeDate);

    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getCity() {
        return mCity;
    }

    public long getTimeDate() {return mTimeDate;}

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(mDate);
    }

    public String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(mDate);
    }


}
