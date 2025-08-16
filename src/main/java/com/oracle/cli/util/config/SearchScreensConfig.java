package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.search.SearchBooksView;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchScreensConfig {
    private final ScreenService service;
    private final SearchBooksView home;

    public SearchScreensConfig(ScreenService service, SearchBooksView home) {
        this.service = service;
        this.home = home;
    }

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.SEARCH_BOOK, home);
    }
}
