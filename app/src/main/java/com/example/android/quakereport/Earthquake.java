package com.example.android.quakereport;

import android.support.v4.content.ContextCompat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    //Magnitude
    private double mMagnitude;

    //Location
    private String mProximity = "Near the";
    private String mCity;

    //Date
    private long mTimeDate;
    private Date mDate;

    //Url
    private String mUrl;

    public Earthquake(double magnitude, String city, long timeDate, String url){
        mMagnitude = magnitude;
        mCity = city;
        mTimeDate = timeDate;
        mUrl = url;
        mDate = new Date(mTimeDate);

        //Separate the location
        separateLocationString();
    }

    public String getMagnitudeString() {
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(mMagnitude);
        return output;
    }

    public double getMagnitudeDouble(){
        return mMagnitude;
    }

    public String getProximity() { return mProximity; }

    public String getCity() {
        return mCity;
    }

    public long getTimeDate() {return mTimeDate; }

    public String getUrl() { return mUrl; }

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
