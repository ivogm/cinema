package com.ivohasablog.cinema.movieservice.persistence.repository;

import com.ivohasablog.cinema.movieservice.persistence.domain.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ivo on 02.12.2017 г..
 */
public interface TicketRepository extends MongoRepository<Ticket, String> {
}
