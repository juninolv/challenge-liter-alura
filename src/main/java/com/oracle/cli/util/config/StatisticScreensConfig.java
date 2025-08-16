package com.oracle.cli.util.config;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.statistic.StatisticsAuthorsLifeView;
import com.oracle.cli.view.statistic.StatisticsBookDownloadView;
import com.oracle.cli.view.statistic.StatisticsView;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StatisticScreensConfig {
    private final ScreenService service;
    private final StatisticsView home;
    private final StatisticsBookDownloadView bookDownload;
    private final StatisticsAuthorsLifeView authorsLife;

    public StatisticScreensConfig(
        ScreenService service,
        StatisticsView home,
        StatisticsBookDownloadView bookDownload,
        StatisticsAuthorsLifeView authorsLife
    ) {
        this.service = service;
        this.home = home;
        this.bookDownload = bookDownload;
        this.authorsLife = authorsLife;
    }

    @PostConstruct
    public void setupScreens() {
        service.setScreen(ScreenSelector.STATISTICS, home);
        service.setScreen(ScreenSelector.STATISTICS_BOOK_DOWNLOAD, bookDownload);
        service.setScreen(ScreenSelector.STATISTICS_AUTHORS_LIFE, authorsLife);
    }
}
