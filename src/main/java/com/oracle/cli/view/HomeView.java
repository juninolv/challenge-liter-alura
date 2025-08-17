package com.oracle.cli.view;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected HomeView(ScreenService service, Scanner input) {
        super("HOME - LITER ALURA");
        this.service = service;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        service.print(this);
        ScreenSelector option = getSelectorByAction();

        return switch (option){
            case HOME -> true;
            case
                SEARCH_BOOK, STATISTICS, BOOKS, AUTHORS ->
                service.show(option);
            default -> false;
        };
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder
            .append("#\n")
            .append("# 1 - Search Books\n")
            .append("# 2 - Books\n")
            .append("# 3 - Authors\n")
            .append("# 0 - Exit\n")
            .append("#\n# -> ");
    }

    private ScreenSelector getSelectorByAction() {
        int action = Integer.parseInt(input.nextLine());

        return switch (action){
            case 1 -> ScreenSelector.SEARCH_BOOK;
            case 2 -> ScreenSelector.BOOKS;
            case 3 -> ScreenSelector.AUTHORS;
            case 0 -> {
                service.println("\n# Leaving...\n");
                yield ScreenSelector.EXIT;
            }
            default -> {
                service.println("\n# Invalid action.");
                yield ScreenSelector.HOME;
            }
        };
    }
}
