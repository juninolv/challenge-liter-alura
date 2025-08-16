package com.oracle.cli.view.book;

import com.oracle.cli.dto.BookDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.BookService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMostDownloadView extends ScreenBase {
    private final ScreenService screenService;
    private final BookService bookService;

    protected BookMostDownloadView(
        ScreenService screenService,
        BookService bookService
    ) {
        super("BOOKS - TOP 10 DOWNLOADS");
        this.screenService = screenService;
        this.bookService = bookService;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        showResult(bookService.readMostDownload());

        return true;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder.append("\n# Checking Database...");
    }

    private void showResult(List<BookDto> data) {
        if (data == null) {
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
