package com.oracle.cli.view.statistic;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StatisticsView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected StatisticsView(ScreenService service, Scanner input) {
        super("STATISTICS - HOME");
        this.service = service;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        service.print(this);
        ScreenSelector option = getSelectorByAction();

        return switch (option){
            case HOME, EXIT -> true;
            case
                STATISTICS_BOOK_DOWNLOAD, STATISTICS_AUTHORS_LIFE ->
                service.show(option);
            default -> false;
        };
    }

    @Override
    protected void render(StringBuilder builder) {
        builder
            .append("# 1 - Books - Download\n")
            .append("# 2 - Author's - Life\n")
            .append("# 0 - Exit\n")
            .append("#\n# -> ");
    }

    private ScreenSelector getSelectorByAction() {
        int action = Integer.parseInt(input.nextLine());

        return switch (action){
            case 1 -> ScreenSelector.STATISTICS_BOOK_DOWNLOAD;
            case 2 -> ScreenSelector.STATISTICS_AUTHORS_LIFE;
            case 0 -> {
                service.println("\n# Leaving...");
                yield ScreenSelector.EXIT;
            }
            default -> {
                service.println("\n# Invalid action.");
                yield ScreenSelector.HOME;
            }
        };
    }
}
