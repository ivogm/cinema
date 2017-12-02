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
            case MovieCE.MOVIE_CACHE_ITEM_ID:
                return new MovieCE();
            case ProjectionCE.PROJECTION_CACHE_ITEM_ID:
                return new ProjectionCE();
            case HallCE.HALL_CACHE_ITEM_ID:
                return new HallCE();
            case TicketCE.TICKET_CACHE_ITEM_ID:
                return new TicketCE();
            default:
                return new CacheEntry();
        }
    }
}
