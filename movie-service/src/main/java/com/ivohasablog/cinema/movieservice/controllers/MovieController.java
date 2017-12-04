package com.ivohasablog.cinema.movieservice.controllers;

import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import com.ivohasablog.cinema.movieservice.service.MovieService;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringComponent
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> getAllMovies() {
        return movieService.retrieveAllMovies();
    }

}
