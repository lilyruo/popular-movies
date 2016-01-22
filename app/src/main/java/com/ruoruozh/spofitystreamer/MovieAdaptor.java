package com.ruoruozh.spofitystreamer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruoruozh.spofitystreamer.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hudi on 1/11/16.
 */
public class MovieAdaptor extends ArrayAdapter<Movie> {
    private long currentPage;

    public MovieAdaptor(Context context, List<Movie> movieList) {
        super(context, 0, movieList);
        currentPage = 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.movie_image);
        Picasso.with(getContext()).load(movie.getImageUrl()).into(imageView);

        TextView titleView = (TextView) convertView.findViewById(R.id.movie_title);
        titleView.setText(movie.getTitle());

        return convertView;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }
}
