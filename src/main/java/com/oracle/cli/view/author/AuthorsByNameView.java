package com.oracle.cli.view.author;

import com.oracle.cli.dto.AuthorDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.AuthorService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AuthorsByNameView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final AuthorService authorService;

    protected AuthorsByNameView(
        ScreenService screenService,
        AuthorService authorService,
        Scanner input
    ) {
        super("AUTHOR'S - FIND BY NAME");
        this.screenService = screenService;
        this.authorService = authorService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            String name = getName();
            screenService.println("\n# Checking Database...");
            showResult(authorService.readByName(name));

            return true;
        } catch (ExitException exception) {
            screenService.println("\n# Leaving...");
            return true;
        } catch (RuntimeException exception) {
            screenService.println("\n# Leaving...");
        }

        return false;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder.append("#\n# 0 - Exit\n");
    }

    @NonNull
    private String getName() {
        screenService.print("# Enter NAME -> ");
        String value = input.nextLine().toLowerCase();
        hasExitCommand(value);

        return value;
    }

    private void showResult(AuthorDto data) {
        if (data == null) {
            screenService.println("\n# No data found!");
            return;
        }

        screenService.printf("%n# Name: %s", data.name());
        screenService.printf("%n# Birth Year's: %s", data.birthYear());
        screenService.printf("%n# Death Year: %s%n", data.deathYear());
    }
}
