package com.ivohasablog.cinema.movieservice.persistence.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ivo on 02.12.2017 г..
 */
@Data
@Document
public class Ticket {
    @Id
    private String id;
    private Hall hall;
    private Double price;
    private Integer seatNumber;
    private SeatStatus status;
}
