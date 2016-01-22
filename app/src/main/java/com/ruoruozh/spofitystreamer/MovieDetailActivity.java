package com.ruoruozh.spofitystreamer;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ruoruozh.spofitystreamer.data.Movie;

import static com.ruoruozh.spofitystreamer.data.Movie.MOVIE_LABEL;

public class MovieDetailActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        movie = (Movie) getIntent().getParcelableExtra(MOVIE_LABEL);
        Log.d(LOG_TAG, "Received intent with move=" + movie);
        FragmentManager fragmentManager = getFragmentManager();
        MovieDetailActivityFragment fragment = (MovieDetailActivityFragment)fragmentManager.findFragmentById(R.id
                .detail_fragment);
        if (fragment != null) {
            fragment.updateMovie(movie);
        }

    }

}
