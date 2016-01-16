package com.ruoruozh.spofitystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ruoruozh.spofitystreamer.data.FetchMovieAsyncTask;
import com.ruoruozh.spofitystreamer.data.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private MovieAdaptor movieAdaptor;
    private FetchMovieAsyncTask fetchMovieAsyncTask;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        movieList.add(new Movie("1", "1Hunger Game", "http://i.imgur.com/KaQpUaHb.jpg"));
        movieList.add(new Movie("2", "2PS2", "http://i.imgur.com/gvOZuJvb.jpg"));
        movieList.add(new Movie("3", "3Angry Birds", "http://i.imgur.com/pGh59qXb.jpg"));
        movieList.add(new Movie("4", "4Heros", "http://i.imgur.com/UB07NkHb.jpg"));
        movieList.add(new Movie("5", "5Curious Story of Benjamin", "http://i.imgur.com/YToAPOZb.jpg"));
        movieList.add(new Movie("6", "6Star Wars", "http://i.imgur.com/ujfDCorb.jpg"));
        movieList.add(new Movie("7", "7Star Trek", "http://i.imgur.com/KkAfQv7b.jpg"));
        movieList.add(new Movie("8", "8Gone Girl", "http://i.imgur.com/tbw2oMib.jpg"));
        movieList.add(new Movie("9", "9Small World", "http://i.imgur.com/DceiGmpb.jpg"));
        movieList.add(new Movie("10", "10Smoking Guns", "http://i.imgur.com/KYwckaub.jpg"));
        return movieList;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.menu_movie_detail, menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (fetchMovieAsyncTask != null) {
            fetchMovieAsyncTask.cancel(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_popularity) {
            Log.d(LOG_TAG, "Sort by popularity menu pressed");
            return true;
        } else if (id == R.id.action_sort_rating) {
            Log.d(LOG_TAG, "Sort by rating menu pressed");
            return true;
        } else if (id == R.id.refresh) {
            refresh();
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void refresh() {
        if (fetchMovieAsyncTask == null) {
            fetchMovieAsyncTask = new FetchMovieAsyncTask(movieAdaptor);
        }
        AsyncTask.Status status = fetchMovieAsyncTask.getStatus();
        if (status == AsyncTask.Status.RUNNING) {
            return;
        }
        fetchMovieAsyncTask.execute();
    }


}
