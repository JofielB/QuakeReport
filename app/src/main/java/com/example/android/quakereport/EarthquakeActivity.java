/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private final String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    boolean isConnected = false;

    ListView earthquakeListView;
    TextView txtEmptyView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);


        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);
        txtEmptyView = (TextView) findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(txtEmptyView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Verify if the user has a internet connection
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        //Get the data using Loaders
        Bundle urls = new Bundle();
        urls.putString("url", url);
        getLoaderManager().initLoader(0, urls, this);

        /*Get and set the data using AsyncTask
        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute(url);

         */
    }

    /**Method from the loader manager that are needed to implement the use of a Loader
     * */
    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, Bundle args) {
        return new EarthQuakeLoader(EarthquakeActivity.this, url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> data) {
        setAdapter(data);

        //Hide the progress bar
        progressBar.setVisibility(View.GONE);

        //Decide what message show to the user depending of his internet connection
        if(isConnected) {
            txtEmptyView.setText(R.string.no_earthquakes);
        }else{
            txtEmptyView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        setAdapter(new ArrayList<Earthquake>());
    }

    /**Reference code: How to use the AsyncTask
    * */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>>
    {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            //Create the HTTP request and generate the list of Earthquakes
            return QueryUtils.extractEarthquakes(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakesList) {
            setAdapter(earthquakesList);
        }

    }

    private void setAdapter(ArrayList<Earthquake> earthquakesList){
        //Final earthquakes
        final ArrayList<Earthquake> earthquakes = earthquakesList;
        // Create a new {@link ArrayAdapter} of earthquakes
        EarthQuakesListAdapter adapter = new EarthQuakesListAdapter(
                EarthquakeActivity.this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = earthquakes.get(position).getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

}
