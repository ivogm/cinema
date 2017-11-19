package com.ivohasablog.cinema.movieservice.cache.serializable;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

/**
 * Created by Ivo on 18.11.2017 г..
 */
public class MovieTheaterDSFactory implements DataSerializableFactory {
    @Override
    public IdentifiedDataSerializable create(int typeId) {
        switch (typeId) {
            case MovieCacheItem.MOVIE_CACHE_ITEM_ID:
                return new MovieCacheItem();
            case 2:
                return new MovieCacheItem();
            default:
                return null;
        }
    }
}
