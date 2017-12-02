package com.ivohasablog.cinema.movieservice.cache.serializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;

import java.io.IOException;

/**
 * Created by Ivo on 02.12.2017 г..
 */
public class HallCE extends CacheEntry {
    public static final int HALL_CACHE_ITEM_ID = 3;

    private String id;
    private int capacity;
    private boolean isAvailable;
    private int numberRows;

    @Override
    public int getId() {
        return HALL_CACHE_ITEM_ID;
    }

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeInt(capacity);
        out.writeBoolean(isAvailable);
        out.writeInt(numberRows);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        id = in.readUTF();
        capacity = in.readInt();
        isAvailable = in.readBoolean();
        numberRows = in.readInt();
    }
}
