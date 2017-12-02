package com.ivohasablog.cinema.movieservice.service;

import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ivo on 03.12.2017 г..
 */
@Service
public class MovieService {

    public List<Movie> retrieveAllMovies() {
        return Collections.emptyList();
    }

}
