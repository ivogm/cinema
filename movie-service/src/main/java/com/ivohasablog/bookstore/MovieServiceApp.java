package com.ivohasablog.bookstore;

import com.ivohasablog.bookstore.persistence.domain.Movie;
import com.ivohasablog.bookstore.persistence.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieServiceApp implements CommandLineRunner{

	@Autowired
	private BookRepository repository;

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
		repository.save(new Movie("Clean Code", "Robert Cecil Martin", "July 17, 2008"));
		repository.save(new Movie("The Clean Coder: A Code of Conduct for Professional Programmers", "Robert Cecil Martin", "May 23rd 2011"));
		repository.save(new Movie("Clean Architecture: A Craftsman's Guide to Software Structure and Design", "Robert Cecil Martin", "September 10, 2017"));

		for(int i=0; i<10000; i++) {
			String title = "Movie " + i;
			String author = "System";
			String date = "February 13, 2017";
			repository.save(new Movie(title, author, date));
		}

		// fetch all customers
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Movie movie : repository.findAll()) {
			System.out.println(movie.toString());
		}
		System.out.println();
	}
}
