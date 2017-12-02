package com.ivohasablog.cinema.movieservice.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;

/**
 * Created by Ivo on 02.12.2017 г..
 */
public class CacheEntry  implements IdentifiedDataSerializable {
    private static final int MOVIE_THEATER_DS_FACTORY = 1;

    @Override
    public int getFactoryId() {
        return MOVIE_THEATER_DS_FACTORY;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {

    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {

    }
}
