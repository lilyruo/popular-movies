package com.ruoruozh.spofitystreamer.data;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.ruoruozh.spofitystreamer.BuildConfig;
import com.ruoruozh.spofitystreamer.MovieAdaptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hudi on 1/15/16.
 */
public class FetchMovieAsyncTask extends AsyncTask<Void, Void, List<Movie>> {

    private final String LOG_TAG = this.getClass().getSimpleName();
    private static final String SORT_BY = "sort_by";
    private static final String API_KEY = "api_key";
    private static final String PAGE = "page";
    private final MovieAdaptor movieAdaptor;
    private final SortOrder sortOrder;
    private long pageToLoad;

    public FetchMovieAsyncTask(MovieAdaptor movieAdaptor, SortOrder sortOrder, boolean clearAll) {
        this.movieAdaptor = movieAdaptor;
        this.sortOrder = sortOrder;
        if (clearAll) {
            pageToLoad = 1;
        } else {
            pageToLoad = movieAdaptor.getCurrentPage() + 1;
        }
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        Log.d(LOG_TAG, "fetching latest movies, sort order=" + sortOrder.getDescription() + ", " +
                "page=" + pageToLoad);
        String movieJson = getMoviesJSON(pageToLoad);
        if (movieJson == null) {
            return Collections.emptyList();
        }
        return MovieDataParser.parseMovieData(movieJson);
    }

    private String getMoviesJSON(long pageNumber) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(buildAPIQueryUrL(pageNumber));
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            if (builder.length() == 0) {
                return null;
            }
            return builder.toString();
        } catch (IOException ioe){
            Log.e(LOG_TAG, "Failed to download movie json", ioe);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing reader", e);
                }
            }
        }
    }

    private String buildAPIQueryUrL(long pageNumber) {
        Uri buildUri = Uri.parse("http://api.themoviedb.org/3/discover/movie?").buildUpon()
                .appendQueryParameter(API_KEY, BuildConfig.THE_MOVIEDB_API_KEY)
                .appendQueryParameter(SORT_BY, sortOrder.getDescription())
                .appendQueryParameter(PAGE, String.valueOf(pageNumber)).build();
        return buildUri.toString();
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        if (movies.isEmpty()) {
            return;
        }
        if (pageToLoad == 1) {
            movieAdaptor.clear();
        }
        movieAdaptor.addAll(movies);
        // set the page number to the one page successfully loaded just now
        movieAdaptor.setCurrentPage(pageToLoad);
        movieAdaptor.notifyDataSetChanged();
    }
}
