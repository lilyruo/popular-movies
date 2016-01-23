package com.ruoruozh.spofitystreamer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruoruozh.spofitystreamer.data.Movie;
import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieDetailActivityFragment extends Fragment {

    private Movie movie;
    public MovieDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        if (movie != null) {
            refreshView(movie);
        }
        return rootView;
    }

    public void updateMovie(Movie movie) {
        this.movie = movie;
        refreshView(movie);
    }

    private void refreshView(Movie movie) {
        View rootView = getView();
        ImageView imageView = (ImageView)rootView.findViewById(R.id.poster);
        Picasso.with(getActivity()).load(movie.getImageUrl()).error(R.drawable
                .error_movie_placeholder).placeholder(R.drawable.movie_placeholder).into(imageView);
        TextView synapsisView = (TextView)rootView.findViewById(R.id.synopsis);
        synapsisView.setText(movie.getSynopsis());
        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        titleView.setText(movie.getTitle());
        TextView releaseDateView = (TextView) rootView.findViewById(R.id.releaseDate);
        releaseDateView.setText(movie.getReleaseDate());
        TextView ratingView = (TextView) rootView.findViewById(R.id.rating);
        ratingView.setText(movie.getRating());
    }
}
