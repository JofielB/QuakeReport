package com.example.android.quakereport;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthQuakesListAdapter extends ArrayAdapter<Earthquake> {


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param earthquakesList A List of Earthquakes objects to display in a list
     */
    public EarthQuakesListAdapter(Activity context, ArrayList<Earthquake> earthquakesList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakesList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_activity, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);


        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.txtMagnitude);

        magnitudeTextView.setText(currentEarthquake.getMagnitude());


        TextView cityTextView = (TextView) listItemView.findViewById(R.id.txtCity);

        cityTextView.setText(currentEarthquake.getCity());


        TextView dateTextView = (TextView) listItemView.findViewById(R.id.txtDate);

        dateTextView.setText(currentEarthquake.getDate());


        return listItemView;
    }
}
