package com.ruoruozh.spofitystreamer.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Hudi on 1/11/16.
 */
public class Movie implements Parcelable {
    public static final String MOVIE_LABEL = "Movie";
    private static final int NUMBER_OF_FIELDS = 7;
    private String id;
    private String title;
    private String imageUrl;
    private String popularity;
    private String rating;
    private String synopsis;
    private String releaseDate;

    public Movie(Parcel in) {
        String[] data = new String[NUMBER_OF_FIELDS];
        in.readStringArray(data);
        id = data[0];
        title = data[1];
        imageUrl = data[2];
        popularity = data[3];
        rating = data[4];
        synopsis = data[5];
        releaseDate = data[6];
    }

    public Movie(String id, String title, String url, String popularity, String rating, String synopsis, String releaseDate) {
        this.id = id;
        this.title = title;
        this.imageUrl = url;
        this.popularity = popularity;
        this.rating = rating;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public Movie(String id, String title, String url) {
        this(id, title, url, null, null, null, null);
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

    public String getPopularity() {
        return popularity;
    }

    public String getRating() {
        return rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        builder.append(id);
        builder.append(' ');
        builder.append(title);
        builder.append(' ');
        builder.append(imageUrl);
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {id, title, imageUrl, popularity, rating, synopsis, releaseDate});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}
