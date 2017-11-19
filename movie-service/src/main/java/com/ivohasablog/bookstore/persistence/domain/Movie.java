package com.ivohasablog.bookstore.persistence.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Id;

/**
 * Created by Ivo on 18.11.2017 г..
 */
@Data
@NoArgsConstructor
@ToString()
public class Movie {
    @Id
    private String id;
    private String title;
    private Double rating;
    private String publishedDate;

    public Movie(String title, Double rating, String publishedDate) {
        this.title = title;
        this.rating = rating;
        this.publishedDate = publishedDate;
    }
}
