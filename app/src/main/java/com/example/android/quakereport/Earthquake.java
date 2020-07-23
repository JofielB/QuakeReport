package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    //Magnitude
    private String mMagnitude;

    //Location
    private String mProximity = "Near the";
    private String mCity;

    //Date
    private long mTimeDate;
    private Date mDate;

    public Earthquake(String magnitude, String city, long timeDate){
        mMagnitude = magnitude;
        mCity = city;
        mTimeDate = timeDate;
        mDate = new Date(mTimeDate);

        //Separate the location
        separateLocationString();
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getProximity() { return mProximity; }

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

    private void separateLocationString(){
        String fullLocation = mCity;
        if(fullLocation.contains(" of"))
        {
            int location = fullLocation.indexOf(" of ");
            mProximity = fullLocation.substring(0,location+3);
            mCity = fullLocation.substring(location+4);
        }
    }

}
