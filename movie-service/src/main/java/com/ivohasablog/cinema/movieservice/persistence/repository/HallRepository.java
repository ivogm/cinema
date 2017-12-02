package com.ivohasablog.cinema.movieservice.persistence.repository;

import com.ivohasablog.cinema.movieservice.persistence.domain.Hall;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ivo on 02.12.2017 г..
 */
public interface HallRepository extends MongoRepository<Hall, String> {
}
