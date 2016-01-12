package com.ruoruozh.spofitystreamer;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hudi on 1/11/16.
 */
public class MovieAdaptor extends ArrayAdapter<Movie> {

    public MovieAdaptor(Context context, List<Movie> movieList) {
        super(context, 0, movieList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        if (movie == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
        // TODO: set image view

        TextView titleView = (TextView) convertView.findViewById(R.id.movie_title);
        titleView.setText(movie.getId());

        return super.getView(position, convertView, parent);
    }
}