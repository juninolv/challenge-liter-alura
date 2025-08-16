package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.book.*;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookScreensConfig {
    private final ScreenService service;
    private final BookMostDownloadView mostDownload;
    private final BooksAllView all;
    private final BooksByLanguageView byLanguage;
    private final BooksBySubjectView bySubject;
    private final BooksView home;

    public BookScreensConfig(
        ScreenService service,
        BookMostDownloadView mostDownload,
        BooksAllView all,
        BooksByLanguageView byLanguage,
        BooksBySubjectView bySubject,
        BooksView home
    ) {
        this.service = service;
        this.mostDownload = mostDownload;
        this.all = all;
        this.byLanguage = byLanguage;
        this.bySubject = bySubject;
        this.home = home;
    }

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.BOOKS, home);
        service.setScreen(ScreenSelector.BOOKS_ALL, all);
        service.setScreen(ScreenSelector.BOOKS_MOST_DOWN, mostDownload);
        service.setScreen(ScreenSelector.BOOKS_LANGUAGE, byLanguage);
        service.setScreen(ScreenSelector.BOOKS_SUBJECT, bySubject);
    }
}
