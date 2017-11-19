package com.ivohasablog.cinema.movieservice.cache.preloader;

import com.hazelcast.core.MapLoader;
import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import com.ivohasablog.cinema.movieservice.persistence.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Ivo on 19.11.2017 г..
 */
@Component
public class MovieMapLoader implements MapLoader<String, Movie> {
    private static final Logger logger = LoggerFactory.getLogger(MovieMapLoader.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie load(String key) {
        return movieRepository.findOne(key);
    }

    @Override
    public Map<String, Movie> loadAll(Collection<String> keys) {
        //if (hazelcastInstance.getCluster().getMembers().iterator().next().localMember()) {
        logger.info("Loading movies to cache...");
        Instant startTime = Instant.now();
        Iterable<Movie> movies = movieRepository.findAll();
        logger.info("Loaded movies to cache in: " + ChronoUnit.MILLIS.between(startTime, Instant.now()));
        return StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toMap(Movie::getId, Function.identity(), (key1, key2) -> key1));
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return Collections.singletonList("");
    }
}
