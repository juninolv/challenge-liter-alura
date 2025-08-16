package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.HomeView;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ScreensConfig {
    private final ScreenService service;
    private final HomeView home;

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.HOME, home);
    }
}
