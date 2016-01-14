package com.ruoruozh.spofitystreamer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by Hudi on 1/11/16.
 */
public class Movie implements Parcelable {
    public static final String MOVIE_LABEL = "Movie";
    private static final int NUMBER_OF_FIELDS = 3;
    private String id;
    private String title;
    private String imageUrl;
    private String synopsis;
    private float rating;
    private Calendar releaseDate;

    public Movie(Parcel in) {
        String[] data = new String[NUMBER_OF_FIELDS];
        in.readStringArray(data);
        id = data[0];
        title = data[1];
        imageUrl = data[2];
    }

    public Movie(String id, String url, String title) {
        this.id = id;
        this.title = title;
        this.imageUrl = url;
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
        dest.writeStringArray(new String[] {id, title, imageUrl});
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
