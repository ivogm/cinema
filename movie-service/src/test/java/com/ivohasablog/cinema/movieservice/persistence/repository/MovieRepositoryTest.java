package com.ivohasablog.cinema.movieservice.persistence.repository;

import com.ivohasablog.cinema.movieservice.MovieServiceAppTests;
import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ivo on 19.11.2017 г..
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
@DataMongoTest
public class MovieRepositoryTest {
    private static final int EXPECTED_MOVIE_COUNT = 2;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        importJSON("movie", "src/test/resources/movies.json");
    }

    @Test
    public void testFindAll() {
        //when
        List<Movie> allMovies = movieRepository.findAll();

        //then
        assertThat(allMovies).hasSize(EXPECTED_MOVIE_COUNT);
    }

    protected void importJSON(String collection, String file) {
        try {
            for (Object line : FileUtils.readLines(new File(file), "utf8")) {
                mongoTemplate.save(line, collection);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not import file: " + file, e);
        }
    }
}
