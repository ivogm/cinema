package com.ivohasablog.cinema.movieservice.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.Data;

import java.io.IOException;

/**
 * Created by Ivo on 19.11.2017 г..
 */
@Data
public class MovieCacheItem implements IdentifiedDataSerializable {
    public static final int MOVIE_CACHE_ITEM_ID = 1;

    private String id;
    private String title;
    private double rating;
    private String releaseDate;

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getId() {
        return MOVIE_CACHE_ITEM_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(title);
        out.writeDouble(rating);
        out.writeUTF(releaseDate);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readUTF();
        title = in.readUTF();
        rating = in.readDouble();
        releaseDate = in.readUTF();
    }
}
