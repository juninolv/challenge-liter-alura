package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.book.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BookScreensConfig {
    private final ScreenService service;
    private final BookMostDownloadView mostDownload;
    private final BooksAllView all;
    private final BooksByLanguageView byLanguage;
    private final BooksBySubjectView bySubject;
    private final BooksView home;

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.BOOKS, home);
        service.setScreen(ScreenSelector.BOOKS_ALL, all);
        service.setScreen(ScreenSelector.BOOKS_MOST_DOWN, mostDownload);
        service.setScreen(ScreenSelector.BOOKS_LANGUAGE, byLanguage);
        service.setScreen(ScreenSelector.BOOKS_SUBJECT, bySubject);
    }
}
