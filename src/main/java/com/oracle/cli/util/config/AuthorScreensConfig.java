package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.author.*;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorScreensConfig {
    private final ScreenService service;
    private final AuthorsView home;
    private final AuthorsByNameView byName;
    private final AuthorsAllView all;
    private final AuthorsAllLivingView allLiving;
    private final AuthorsAllDeadView allDead;

    public AuthorScreensConfig(
        ScreenService service,
        AuthorsView home,
        AuthorsByNameView byName,
        AuthorsAllView all,
        AuthorsAllLivingView allLiving,
        AuthorsAllDeadView allDead
    ) {
        this.service = service;
        this.home = home;
        this.byName = byName;
        this.all = all;
        this.allLiving = allLiving;
        this.allDead = allDead;
    }

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.AUTHORS, home);
        service.setScreen(ScreenSelector.AUTHORS_ALL, all);
        service.setScreen(ScreenSelector.AUTHORS_ALL_LIVING, allLiving);
        service.setScreen(ScreenSelector.AUTHORS_ALL_DEAD, allDead);
        service.setScreen(ScreenSelector.AUTHORS_NAME, byName);
    }
}
