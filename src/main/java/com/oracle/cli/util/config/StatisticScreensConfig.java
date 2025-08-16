package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.statistic.StatisticsAuthorsLifeView;
import com.oracle.cli.view.statistic.StatisticsBookDownloadView;
import com.oracle.cli.view.statistic.StatisticsView;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class StatisticScreensConfig {
    private final ScreenService service;
    private final StatisticsView home;
    private final StatisticsBookDownloadView bookDownload;
    private final StatisticsAuthorsLifeView authorsLife;

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.STATISTICS, home);
        service.setScreen(ScreenSelector.STATISTICS_BOOK_DOWNLOAD, bookDownload);
        service.setScreen(ScreenSelector.STATISTICS_AUTHORS_LIFE, authorsLife);
    }
}
