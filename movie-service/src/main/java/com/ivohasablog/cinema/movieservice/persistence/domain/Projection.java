package com.ivohasablog.cinema.movieservice.persistence.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by Ivo on 02.12.2017 г..
 */
@Data
@Document
public class Projection {

    @Id
    private String id;
    private Movie movie;
    private Hall hall;
    private LocalDateTime startTime;
}
