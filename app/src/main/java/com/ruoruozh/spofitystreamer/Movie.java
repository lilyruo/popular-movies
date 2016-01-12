package com.ruoruozh.spofitystreamer;

import java.util.Calendar;

/**
 * Created by Hudi on 1/11/16.
 */
public class Movie {
    private String id;
    private String title;
    private String imageUrl;
    private String synopsis;
    private float rating;
    private Calendar releaseDate;

    public Movie(String id, String url, String title) {
        this.id = id;
        this.imageUrl = url;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
