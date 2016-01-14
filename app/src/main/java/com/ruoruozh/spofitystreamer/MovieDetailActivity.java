package com.ruoruozh.spofitystreamer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import static com.ruoruozh.spofitystreamer.Movie.*;

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
    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
