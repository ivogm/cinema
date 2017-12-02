package com.ivohasablog.cinema.movieservice.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import lombok.Data;

import java.io.IOException;

/**
 * Created by Ivo on 02.12.2017 г..
 */
@Data
public class ProjectionCE extends CacheEntry {
    public static final int PROJECTION_CACHE_ITEM_ID = 2;

    private String id;
    private String movieId;
    private String hallId;
    private String startTime;

    @Override
    public int getId() {
        return PROJECTION_CACHE_ITEM_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(movieId);
        out.writeUTF(hallId);
        out.writeUTF(startTime);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readUTF();
        movieId = in.readUTF();
        hallId = in.readUTF();
        startTime = in.readUTF();
    }
}
