package com.oracle.cli.view.author;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AuthorsView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected AuthorsView(ScreenService service, Scanner input) {
        super("AUTHOR'S - HOME");
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
                AUTHORS_ALL,
                AUTHORS_ALL_LIVING,
                AUTHORS_ALL_DEAD,
                AUTHORS_NAME ->
                service.show(option);
            default -> false;
        };
    }

    @Override
    protected void render(StringBuilder builder) {
        builder
            .append("# 1 - All\n")
            .append("# 2 - All Living\n")
            .append("# 3 - All Dead\n")
            .append("# 4 - By Name\n")
            .append("# 0 - Exit\n")
            .append("#\n# -> ");
    }

    private ScreenSelector getSelectorByAction() {
        int action = Integer.parseInt(input.nextLine());

        return switch (action){
            case 1 -> ScreenSelector.AUTHORS_ALL;
            case 2 -> ScreenSelector.AUTHORS_ALL_LIVING;
            case 3 -> ScreenSelector.AUTHORS_ALL_DEAD;
            case 4 -> ScreenSelector.AUTHORS_NAME;
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
