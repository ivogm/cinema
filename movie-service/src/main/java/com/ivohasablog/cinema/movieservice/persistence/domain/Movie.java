package com.ivohasablog.cinema.movieservice.persistence.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Data
@NoArgsConstructor
@ToString()
@Document
public class Movie {
    @Id
    private String id;
    private String title;
    private Double rating;
    private String releaseDate;

    public Movie(String title, Double rating, String releaseDate) {
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
}
