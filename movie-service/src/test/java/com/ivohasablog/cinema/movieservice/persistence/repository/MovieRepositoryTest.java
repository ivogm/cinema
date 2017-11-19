package com.ivohasablog.cinema.movieservice.persistence.repository;

import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Ivo on 19.11.2017 г..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    private static final int EXPECTED_MOVIE_COUNT = 4;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindAll() {
        //when
        List<Movie> allMovies = movieRepository.findAll();

        //then
        assertThat(allMovies).hasSize(EXPECTED_MOVIE_COUNT);
    }
}
