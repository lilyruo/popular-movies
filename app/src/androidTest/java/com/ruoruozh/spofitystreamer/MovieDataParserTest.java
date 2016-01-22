package com.ruoruozh.spofitystreamer;

import android.test.AndroidTestCase;

import com.ruoruozh.spofitystreamer.data.Movie;
import com.ruoruozh.spofitystreamer.data.MovieDataParser;

import junit.framework.Assert;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Hudi on 1/15/16.
 */
public class MovieDataParserTest extends AndroidTestCase {

    private static final String SAMPLE_JSON = "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"poster_path\": \"/fYzpM9GmpBlIC893fNjoWCwE24H.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Thirty years after defeating the Galactic Empire, Han Solo and his allies face a new threat from the evil Kylo Ren and his army of Stormtroopers.\",\n" +
            "      \"release_date\": \"2015-12-18\",\n" +
            "      \"genre_ids\": [\n" +
            "        28,\n" +
            "        12,\n" +
            "        878,\n" +
            "        14\n" +
            "      ],\n" +
            "      \"id\": 140607,\n" +
            "      \"original_title\": \"Star Wars: The Force Awakens\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"Star Wars: The Force Awakens\",\n" +
            "      \"backdrop_path\": \"/njv65RTipNSTozFLuF85jL0bcQe.jpg\",\n" +
            "      \"popularity\": 47.970708,\n" +
            "      \"vote_count\": 2212,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.9\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.\",\n" +
            "      \"release_date\": \"2015-12-25\",\n" +
            "      \"genre_ids\": [\n" +
            "        37,\n" +
            "        18,\n" +
            "        12,\n" +
            "        53\n" +
            "      ],\n" +
            "      \"id\": 281957,\n" +
            "      \"original_title\": \"The Revenant\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Revenant\",\n" +
            "      \"backdrop_path\": \"/6vb1S6H3FD6UQCjza78TptPB8GL.jpg\",\n" +
            "      \"popularity\": 37.553409,\n" +
            "      \"vote_count\": 383,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 7.08\n" +
            "    },\n" +
            "    {\n" +
            "      \"poster_path\": \"/noUp0XOqIcmgefRnRZa1nhtRvWO.jpg\",\n" +
            "      \"adult\": false,\n" +
            "      \"overview\": \"Based on the real life story of legendary cryptanalyst Alan Turing, the film portrays the nail-biting race against time by Turing and his brilliant team of code-breakers at Britain's top-secret Government Code and Cypher School at Bletchley Park, during the darkest days of World War II.\",\n" +
            "      \"release_date\": \"2014-11-14\",\n" +
            "      \"genre_ids\": [\n" +
            "        36,\n" +
            "        18,\n" +
            "        53,\n" +
            "        10752\n" +
            "      ],\n" +
            "      \"id\": 205596,\n" +
            "      \"original_title\": \"The Imitation Game\",\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"title\": \"The Imitation Game\",\n" +
            "      \"backdrop_path\": \"/fii9tPZTpy75qOCJBulWOb0ifGp.jpg\",\n" +
            "      \"popularity\": 12.968822,\n" +
            "      \"vote_count\": 2329,\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_results\": 251567,\n" +
            "  \"total_pages\": 12579\n" +
            "}";

    public void testParseTotalPages() {
        long totalPages = MovieDataParser.parseTotalPage(SAMPLE_JSON);
        Assert.assertEquals(12579, totalPages);
    }

    public void testParseMovieData() throws JSONException {
        List<Movie> movies = MovieDataParser.parseMovieData(SAMPLE_JSON);
        Assert.assertEquals(3, movies.size());

        Movie first = movies.get(0);
        verifyFirstMovie(first);

        Movie second = movies.get(1);
        verifySecondMovie(second);

        Movie third = movies.get(2);
        verifyThirdMovie(third);
    }

    private void verifyFirstMovie(Movie first) {
        Assert.assertEquals("Star Wars: The Force Awakens", first.getTitle());
        Assert.assertEquals("140607", first.getId());
        int index = first.getImageUrl().lastIndexOf("/");
        Assert.assertEquals("http://image.tmdb.org/t/p/w185/fYzpM9GmpBlIC893fNjoWCwE24H.jpg", first.getImageUrl().substring(index));
        Assert.assertEquals("7.9", first.getRating());
        Assert.assertEquals("2015-12-18", first.getReleaseDate());
        Assert.assertEquals("47.970708", first.getPopularity());
        Assert.assertEquals("Thirty years after defeating the Galactic Empire, Han Solo and his allies face a new threat from the evil Kylo Ren and his army of Stormtroopers.", first.getSynopsis());
    }

    private void verifySecondMovie(Movie second) {
        Assert.assertEquals("The Revenant", second.getTitle());
        Assert.assertEquals("281957", second.getId());
        int index = second.getImageUrl().lastIndexOf('/');
        Assert.assertEquals("http://image.tmdb.org/t/p/w185/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg", second.getImageUrl().substring(index));
        Assert.assertEquals("7.08", second.getRating());
        Assert.assertEquals("2015-12-25", second.getReleaseDate());
        Assert.assertEquals("37.553409", second.getPopularity());
        Assert.assertEquals("In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.", second.getSynopsis());
    }

    private void verifyThirdMovie(Movie third) {
        Assert.assertEquals("The Imitation Game", third.getTitle());
        Assert.assertEquals("205596", third.getId());
        int index = third.getImageUrl().lastIndexOf('/');
        Assert.assertEquals("http://image.tmdb.org/t/p/w185/noUp0XOqIcmgefRnRZa1nhtRvWO.jpg", third.getImageUrl().substring(index));
        Assert.assertEquals("8", third.getRating());
        Assert.assertEquals("2014-11-14", third.getReleaseDate());
        Assert.assertEquals("12.968822", third.getPopularity());
        Assert.assertEquals("Based on the real life story of legendary cryptanalyst Alan Turing, the film portrays the nail-biting race against time by Turing and his brilliant team of code-breakers at Britain's top-secret Government Code and Cypher School at Bletchley Park, during the darkest days of World War II.", third.getSynopsis());

    }
}
