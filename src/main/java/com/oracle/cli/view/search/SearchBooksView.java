package com.oracle.cli.view.search;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchBooksView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected SearchBooksView(ScreenService service, Scanner input) {
        super("SEARCH BOOK - HOME");
        this.service = service;
        this.input = input;
    }

    @Override
    public boolean filter(ScreenSelector selector) {
        service.print(this);
        service.skip();

        return true;
    }

    @Override
    protected void render(StringBuilder builder) {

    }
}
