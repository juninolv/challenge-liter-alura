package com.oracle.cli.view.author;

import com.oracle.cli.model.ScreenSelector;
import com.oracle.cli.service.ScreenService;
import com.oracle.cli.view.ScreenBase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AuthorsAllDeadView extends ScreenBase {
    private final Scanner input;
    private final ScreenService service;

    protected AuthorsAllDeadView(ScreenService service, Scanner input) {
        super("AUTHOR'S - FIND DEAD");
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
