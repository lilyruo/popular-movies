package com.ruoruozh.spofitystreamer.data;

/**
 * Created by ruoruozhang on 1/16/16.
 */
public enum SortOrder {
    RATING("vote_average.desc"),
    POPULARITY("popularity.desc");

    private final String description;

    SortOrder(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SortOrder fromValue(String description) {
        if (description.equals("vote_average.desc")) {
            return RATING;
        } else if (description.equals("popularity.desc")) {
            return POPULARITY;
        } else {
            return null;
        }
    }
}
