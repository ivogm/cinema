package com.ivohasablog.cinema.movieservice;

import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import com.ivohasablog.cinema.movieservice.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration;

@SpringBootApplication(exclude = HazelcastJpaDependencyAutoConfiguration.class)
public class MovieServiceApp implements CommandLineRunner{

	@Autowired
	private MovieRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Movie("It", 6.9, "September 15, 1990"));
		repository.save(new Movie("Carrie", 7.5, "April 5, 1974"));
		repository.save(new Movie("The Shining", 9.0, "January 28, 1977"));
		repository.save(new Movie("The Green Mile", 10.0, "August 29, 1996"));

		// fetch all customers
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Movie movie : repository.findAll()) {
			System.out.println(movie.toString());
		}
		System.out.println();
	}
}
