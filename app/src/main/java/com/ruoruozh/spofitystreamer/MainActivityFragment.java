package com.ruoruozh.spofitystreamer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ruoruozh.spofitystreamer.data.FetchMovieAsyncTask;
import com.ruoruozh.spofitystreamer.data.Movie;
import com.ruoruozh.spofitystreamer.data.SortOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private static final String SORT_ORDER = "sort_order";
    private static final String MOVIES = "movies";

    private MovieAdaptor movieAdaptor;
    private FetchMovieAsyncTask fetchMovieAsyncTask;
    private SortOrder sortOrder;
    private ArrayList<Movie> movieList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        readFromSharedPreference();
        if (savedInstanceState == null || savedInstanceState.containsKey(MOVIES)) {
            movieList = new ArrayList<>();
        } else {
            movieList = savedInstanceState.getParcelableArrayList(MOVIES);
        }

    }

    private void readFromSharedPreference() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences.contains(SORT_ORDER)) {
            sortOrder = SortOrder.fromValue(sharedPreferences.getString(SORT_ORDER, SortOrder
                    .POPULARITY.getDescription()));
        } else {
            sortOrder = SortOrder.POPULARITY;
        }
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
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    refresh(sortOrder, false);
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        movieAdaptor = new MovieAdaptor(getActivity(), movieList);
        gridView.setAdapter(movieAdaptor);

        // trigger a refresh of the movies
        refresh(sortOrder, true);

        return rootView;
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
    public void onPause() {
        super.onPause();
        saveToSharedPreference();
    }

    private void saveToSharedPreference() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SORT_ORDER, sortOrder.getDescription());
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(MOVIES, movieList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sort_popularity) {
            Log.d(LOG_TAG, "Sort by popularity menu pressed");
            if (sortOrder != SortOrder.POPULARITY) {
                refresh(SortOrder.POPULARITY, true);
                sortOrder = SortOrder.POPULARITY;
            }
            return true;
        } else if (id == R.id.action_sort_rating) {
            Log.d(LOG_TAG, "Sort by rating menu pressed");
            if (sortOrder != SortOrder.RATING) {
                refresh(SortOrder.RATING, true);
                sortOrder = SortOrder.RATING;
            }
            return true;
        } else if (id == R.id.refresh) {
            refresh(sortOrder, true);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void refresh(SortOrder sortOrder, boolean clearAll) {
        if (fetchMovieAsyncTask != null) {
            fetchMovieAsyncTask.cancel(true);
        }
        fetchMovieAsyncTask = new FetchMovieAsyncTask(movieAdaptor, sortOrder, clearAll);
        fetchMovieAsyncTask.execute();
    }


}
