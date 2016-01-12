package com.ruoruozh.spofitystreamer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MovieAdaptor movieAdaptor;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.movies_grid);
        movieAdaptor = new MovieAdaptor(getActivity(), getMovies());
        gridView.setAdapter(movieAdaptor);

        return rootView;
    }

    private List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie("AAA", "url1", "Hunger Game"));
        return movieList;
    }
}
