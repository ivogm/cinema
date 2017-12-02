package com.ivohasablog.cinema.movieservice.cache.serializable;

import lombok.Data;

/**
 * Created by Ivo on 02.12.2017 г..
 */
@Data
public class TicketCE extends CacheEntry {
    public static final int TICKET_CACHE_ITEM_ID = 4;

    private String id;
    private String hallId;
    private double price;
    private int seatNumber;
    private String status;

    @Override
    public int getId() {
        return TICKET_CACHE_ITEM_ID;
    }
}
