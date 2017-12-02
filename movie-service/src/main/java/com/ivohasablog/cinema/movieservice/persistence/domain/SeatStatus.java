package com.ivohasablog.cinema.movieservice.persistence.domain;

/**
 * Created by Ivo on 02.12.2017 г..
 */
public enum SeatStatus {
    AVAILABLE("Available"),
    RESERVED("Reserved");

    private String description;

    SeatStatus(String description) {
        this.description = description;
    }
}
