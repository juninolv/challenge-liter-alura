package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.search.SearchBooksView;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SearchScreensConfig {
    private final ScreenService service;
    private final SearchBooksView home;

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.SEARCH_BOOK, home);
    }
}
