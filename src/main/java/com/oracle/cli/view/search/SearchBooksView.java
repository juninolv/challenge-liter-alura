package com.oracle.cli.view.search;

import com.oracle.cli.dto.BookResDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.service.SearchBookService;
import com.oracle.cli.util.exception.ExitException;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchBooksView extends ScreenBase {
    private final Scanner input;
    private final ScreenService screenService;
    private final SearchBookService searchService;

    protected SearchBooksView(
        ScreenService screenService,
        SearchBookService searchService,
        Scanner input
    ) {
        super("SEARCH BOOK - HOME");
        this.screenService = screenService;
        this.searchService = searchService;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        try {
            String title = getTitle();
            screenService.println("\n# Fetching...");

            BookResDto data = searchService.search(title);

            // code here for persistence on database

            showResult(data);
        } catch (ExitException exception) {
            screenService.println("\n# Leaving...");
        }

        return true;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder.append("# 0 - Exit\n");
    }

    private String getTitle() {
        screenService.print("# Enter TITLE -> ");
        String value = input.nextLine();
        hasExitCommand(value);

        return value;
    }

    private void showResult(BookResDto data) {
        if (data == null) {
            screenService.println("\n# No data found!");
            return;
        }

        screenService.printf("%n# Title: %s", data.title());
        screenService.printf("%n# Author's: %s", data.authors());
        screenService.printf("%n# Languages: %s", data.languages());
        screenService.printf("%n# Downloads: %s", data.downloads());
        screenService.skip();
    }
}
