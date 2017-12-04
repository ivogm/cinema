package com.ivohasablog.cinema.movieservice.views;

import com.ivohasablog.cinema.movieservice.controllers.MovieController;
import com.ivohasablog.cinema.movieservice.persistence.domain.Movie;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

@SpringComponent
@Scope("session")
public class MoviesLayout extends VerticalLayout {
    private final MovieController movieController;

    private final Grid<Movie> grid;

    @Autowired
    public MoviesLayout(MovieController movieController) {
        this.movieController = movieController;

        this.grid = new Grid<>();
    }

    @PostConstruct
    void init() {
        final VerticalLayout root = new VerticalLayout();
        setupMovieGrid();

        root.addComponents(grid);
        addComponent(root);
    }

    private void setupMovieGrid() {
        grid.getEditor().setEnabled(true);
        grid.setItems(movieController.getAllMovies());
    }
}
