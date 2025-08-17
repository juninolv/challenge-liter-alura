package com.oracle.cli.view.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.oracle.cli.dto.BookDto;
import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.BookService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.service.SearchBookService;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Component
public class SearchBooksView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final SearchBookService searchService;
    private final BookService bookService;

    protected SearchBooksView(
        ScreenService screenService,
        SearchBookService searchService,
        BookService bookService,
        Scanner input
    ) {
        super("SEARCH BOOK - HOME");
        this.screenService = screenService;
        this.searchService = searchService;
        this.bookService = bookService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            String title = getTitle();
            screenService.println("\n# Checking Database...");
            BookDto result = bookService.readIfExistsByTitle(title);

            if (result != null) {
                showResult(result);
                return true;
            }

            screenService.println("\n# Not found on Database\n\n# Fetching...");
            BookResDto data = searchService.search(title);

            if (data == null) {
                screenService.println("\n# Not found");
                return true;
            }

            result = bookService.create(data);
            showResult(result);
            return true;
        } catch (ExitException exception) {
            screenService.println("\n# Leaving...");
            return true;
        } catch (ExecutionException | InterruptedException | JsonProcessingException exception) {
            System.out.println("\n# Server offline or invalid resource. Leaving...");
            Thread.currentThread().interrupt();
        }

        return false;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder.append("#\n# 0 - Exit\n");
    }

    private String getTitle() {
        screenService.print("# Enter TITLE -> ");
        String value = input.nextLine();
        hasExitCommand(value);

        return value;
    }

    private void showResult(BookDto data) {
        if (data == null) {
            screenService.println("\n# No data found!");
            return;
        }

        screenService.printf("%n# Title: %s", data.title());
        screenService.printf("%n# Author's: %s", data.joinAuthor());
        screenService.printf("%n# Languages: %s", data.languages());
        screenService.printf("%n# Downloads: %s%n", data.downloads());
    }
}
