package com.oracle.cli.view.book;

import com.oracle.cli.dto.BookDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.BookService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class BooksByLanguageView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final BookService bookService;

    protected BooksByLanguageView(
        ScreenService screenService,
        BookService bookService,
        Scanner input
    ) {
        super("BOOKS - FIND BY LANGUAGE");
        this.screenService = screenService;
        this.bookService = bookService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            String language = getLanguageCode();
            screenService.println("\n# Checking Database...");
            showResult(bookService.readByLanguage(language));

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
        builder.append("# 0 - Exit\n");
    }

    @NonNull
    private String getLanguageCode() {
        screenService.print("# Enter LANGUAGE -> ");
        String value = input.nextLine().toLowerCase();
        hasExitCommand(value);

        return value;
    }

    private void showResult(@NonNull List<BookDto> data) {
        if (data.isEmpty()) {
            screenService.println("\n# No data found!");
            return;
        }

        for (BookDto book : data) {
            screenService.printf("%n# Title: %s", book.title());
            screenService.printf("%n# Author's: %s", book.joinAuthor());
            screenService.printf("%n# Languages: %s", book.languages());
            screenService.printf("%n# Downloads: %s%n", book.downloads());
        }
    }
}
