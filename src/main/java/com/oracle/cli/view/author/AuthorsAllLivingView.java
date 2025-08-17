package com.oracle.cli.view.author;

import com.oracle.cli.dto.AuthorDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.AuthorService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AuthorsAllLivingView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final AuthorService authorService;

    protected AuthorsAllLivingView(
        ScreenService screenService,
        AuthorService authorService,
        Scanner input
    ) {
        super("AUTHOR'S - FIND LIVING");
        this.screenService = screenService;
        this.authorService = authorService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            int year = getYear();
            screenService.println("\n# Checking Database...");
            showResult(authorService.readAllLiving(year));

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

    private int getYear() {
        screenService.print("# Enter YEAR -> ");
        String value = input.nextLine().toLowerCase();
        hasExitCommand(value);

        return Integer.parseInt(value);
    }

    private void showResult(@NonNull List<AuthorDto> data) {
        if (data.isEmpty()) {
            screenService.println("\n# No data found!");
            return;
        }

        for (AuthorDto book : data) {
            screenService.printf("%n# Name: %s", book.name());
            screenService.printf("%n# Birth Year's: %s", book.birthYear());
            screenService.printf("%n# Death Year: %s%n", book.deathYear());
        }
    }
}
