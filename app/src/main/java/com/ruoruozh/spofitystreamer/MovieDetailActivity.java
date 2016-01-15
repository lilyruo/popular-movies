package com.ruoruozh.spofitystreamer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.ruoruozh.spofitystreamer.Movie.MOVIE_LABEL;

public class MovieDetailActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupVideo();

    }

    private void setupVideo() {

        ImageView imageView = (ImageView) findViewById(R.id.poster);
        Picasso.with(this).load("https://wordifications.files.wordpress.com/2012/03/the-hunger-games-movie-poster-12162011.jpg").into(imageView);

        TextView synopsisView = (TextView) findViewById(R.id.synopsis);
        synopsisView.setText("Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games, a televised competition in which two teenagers from each of the twelve Districts of Panem are chosen at random to fight to the death.");
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

}
