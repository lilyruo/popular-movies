package com.ruoruozh.spofitystreamer.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hudi on 1/15/16.
 */
public class MovieDataParser {

    private static final String LOG_TAG = "MovieDataParser";

    private static final String RESULTS = "results";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String ID = "id";
    private static final String POPULARITY = "popularity";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String POSTER_PATH = "poster_path";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String IMAGE_PREFIX = "http://image.tmdb.org/t/p/w185";


    public static List<Movie> parseMovieData(String jsonText) {
        List<Movie> movies = new ArrayList<>();
        JSONObject rootJsonObj = null;
        try {
            rootJsonObj = new JSONObject(jsonText);
            JSONArray results = rootJsonObj.getJSONArray(RESULTS);
            for (int i = 0; i < results.length(); i++) {
                JSONObject result = results.getJSONObject(i);
                Movie movie = createMovieFromJson(result);
                if (movie != null) {
                    movies.add(movie);
                }
            }
        } catch (JSONException e) {
        }
        return movies;
    }

    private static Movie createMovieFromJson(JSONObject jsonObject) {
        try {
            return new Movie(jsonObject.getString(ID), jsonObject.getString(ORIGINAL_TITLE), IMAGE_PREFIX + jsonObject.getString(POSTER_PATH), jsonObject.getString(POPULARITY), jsonObject.getString(VOTE_AVERAGE), jsonObject.getString(OVERVIEW), jsonObject.getString(RELEASE_DATE));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Can't create Movie object from JSON");
            return null;
        }
    }

}
