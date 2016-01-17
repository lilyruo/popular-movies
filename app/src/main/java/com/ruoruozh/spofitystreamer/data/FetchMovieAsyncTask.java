package com.ruoruozh.spofitystreamer.data;

import android.os.AsyncTask;
import android.util.Log;

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
    private final MovieAdaptor movieAdaptor;
    private final SortOrder sortOrder;

    public FetchMovieAsyncTask(MovieAdaptor movieAdaptor, SortOrder sortOrder) {
        this.movieAdaptor = movieAdaptor;
        this.sortOrder = sortOrder;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {
        Log.d(LOG_TAG, "fetching latest movies, sort order=" + sortOrder.getDescription());
        String movieJson = getMoviesJSON();
        if (movieJson == null) {
            return Collections.emptyList();
        }
        return MovieDataParser.parseMovieData(movieJson);
    }

    private String getMoviesJSON() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://api.themoviedb" +
                    ".org/3/discover/movie?api_key=c2b3c34fb570e57a200c4c1ee131f1f6&sort_by=" +
                    sortOrder.getDescription());
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

    @Override
    protected void onPostExecute(List<Movie> movies) {
        movieAdaptor.clear();
        movieAdaptor.addAll(movies);
        movieAdaptor.notifyDataSetChanged();
    }
}
