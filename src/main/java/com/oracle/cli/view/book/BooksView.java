package com.oracle.cli.view.book;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BooksView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected BooksView(ScreenService service, Scanner input) {
        super("AUTHORS - HOME");
        this.service = service;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        service.print(this);
        ScreenSelector option = getSelectorByAction();

        return switch (option){
            case HOME, EXIT -> true;
            case BOOKS_ALL, BOOKS_MOST_DOWN, BOOKS_LANGUAGE, BOOKS_SUBJECT ->
                service.show(option);
            default -> false;
        };
    }

    @Override
    protected void render(StringBuilder builder) {
        builder
            .append("# 1 - All\n")
            .append("# 2 - Top 10\n")
            .append("# 3 - By Language\n")
            .append("# 4 - By Subjects\n")
            .append("# 0 - Exit\n")
            .append("#\n# -> ");
    }

    private ScreenSelector getSelectorByAction() {
        int action = Integer.parseInt(input.nextLine());

        return switch (action){
            case 1 -> ScreenSelector.BOOKS_ALL;
            case 2 -> ScreenSelector.BOOKS_MOST_DOWN;
            case 3 -> ScreenSelector.BOOKS_LANGUAGE;
            case 4 -> ScreenSelector.BOOKS_SUBJECT;
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
