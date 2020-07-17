package com.example.android.quakereport;

public class Earthquake {
    private float mMagnitude;
    private String mCity;
    private String mDate;

    public Earthquake(float magnitude, String city, String date){
        mMagnitude = magnitude;
        mCity = city;
        mDate = date;
    }

    public float getmMagnitude() {
        return mMagnitude;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmDate() {
        return mDate;
    }
}
