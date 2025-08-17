package com.oracle.cli.view.author;

import com.oracle.cli.dto.AuthorDto;
import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.AuthorService;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorsAllView extends ScreenBase {
    private final ScreenService screenService;
    private final AuthorService authorService;

    protected AuthorsAllView(
        ScreenService screenService,
        AuthorService authorService
    ) {
        super("AUTHOR'S - FIND ALL");
        this.screenService = screenService;
        this.authorService = authorService;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        screenService.print(this);
        screenService.skip();

        showResult(authorService.readAll());

        return true;
    }

    @Override
    protected void render(@NonNull StringBuilder builder) {
        builder.append("\n# Checking Database...");
    }

    private void showResult(@NonNull List<AuthorDto> data) {
        if (data.isEmpty()) {
            screenService.println("\n# No data found!");
        }

        for (AuthorDto book : data) {
            screenService.printf("%n# Name: %s", book.name());
            screenService.printf("%n# Birth Year's: %s", book.birthYear());
            screenService.printf("%n# Death Year: %s%n", book.deathYear());
        }
    }
}
