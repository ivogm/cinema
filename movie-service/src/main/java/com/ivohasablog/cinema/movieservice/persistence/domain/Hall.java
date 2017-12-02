package com.ivohasablog.cinema.movieservice.persistence.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Ivo on 02.12.2017 г..
 */
@Data
@Document
public class Hall {

    @Id
    private String id;
    private int capacity;
    private boolean isAvailable;
    private int numberRows;
    private Collection<Ticket> tickets;

    public Hall() {
        tickets = new ArrayList<>(capacity);
    }
}
