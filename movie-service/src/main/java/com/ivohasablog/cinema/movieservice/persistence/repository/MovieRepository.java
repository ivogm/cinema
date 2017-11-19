package com.ivohasablog.cinema.movieservice.persistence.repository;

import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ivo on 18.11.2017 г..
 */
public interface MovieRepository extends MongoRepository<Movie, String> {
}
