package com.ivohasablog.cinema.movieservice.views;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme(ValoTheme.THEME_NAME)
public class MovieServiceUI extends UI {
    private static final String WEBSITE_TITLE = "Movie Manager";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle(WEBSITE_TITLE);

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);
    }
}
