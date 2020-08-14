package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

public class EarthQuakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    String mUrl = "";
    public EarthQuakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        return QueryUtils.extractEarthquakes(mUrl);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //Triggers the loader to start the background work
        forceLoad();
    }
}
