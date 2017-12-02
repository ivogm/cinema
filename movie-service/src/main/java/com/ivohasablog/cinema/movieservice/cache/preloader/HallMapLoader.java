package com.ivohasablog.cinema.movieservice.cache.preloader;

import com.hazelcast.core.MapLoader;
import com.ivohasablog.cinema.movieservice.persistence.domain.Hall;
import com.ivohasablog.cinema.movieservice.persistence.repository.HallRepository;
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
 * Created by Ivo on 03.12.2017 г..
 */
@Component
public class HallMapLoader implements MapLoader<String, Hall> {
    private static final Logger logger = LoggerFactory.getLogger(HallMapLoader.class);

    @Autowired
    private HallRepository hallRepository;

    @Override
    public Hall load(String key) {
        return hallRepository.findOne(key);
    }

    @Override
    public Map<String, Hall> loadAll(Collection<String> keys) {
        logger.info("Loading movies to cache...");
        Instant startTime = Instant.now();
        Iterable<Hall> halls = hallRepository.findAll();
        logger.info("Loaded movies to cache in: " + ChronoUnit.MILLIS.between(startTime, Instant.now()));
        return StreamSupport.stream(halls.spliterator(), false).collect(Collectors.toMap(Hall::getId, Function.identity(), (key1, key2) -> key1));
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return Collections.singleton("");
    }
}
