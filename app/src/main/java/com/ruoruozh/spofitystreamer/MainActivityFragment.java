package com.ruoruozh.spofitystreamer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MovieAdaptor movieAdaptor;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.movies_grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);
                Log.d(LOG_TAG, movie.toString());
                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                intent.putExtra(Movie.MOVIE_LABEL, movie);
                startActivity(intent);
            }
        });
        movieAdaptor = new MovieAdaptor(getActivity(), getMovies());
        gridView.setAdapter(movieAdaptor);

        return rootView;
    }

    private List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie("1", "http://i.imgur.com/KaQpUaHb.jpg", "1Hunger Game"));
        movieList.add(new Movie("2", "http://i.imgur.com/gvOZuJvb.jpg", "2PS2"));
        movieList.add(new Movie("3", "http://i.imgur.com/pGh59qXb.jpg", "3Angry Birds"));
        movieList.add(new Movie("4", "http://i.imgur.com/UB07NkHb.jpg", "4Heros"));
        movieList.add(new Movie("5", "http://i.imgur.com/YToAPOZb.jpg", "5Curious Story of Benjamin"));
        movieList.add(new Movie("6", "http://i.imgur.com/ujfDCorb.jpg", "6Star Wars"));
        movieList.add(new Movie("7", "http://i.imgur.com/KkAfQv7b.jpg", "7Star Trek"));
        movieList.add(new Movie("8", "http://i.imgur.com/tbw2oMib.jpg", "8Gone Girl"));
        movieList.add(new Movie("9", "http://i.imgur.com/DceiGmpb.jpg", "9Small World"));
        movieList.add(new Movie("10", "http://i.imgur.com/KYwckaub.jpg", "10Smoking Guns"));
        return movieList;
    }
}
