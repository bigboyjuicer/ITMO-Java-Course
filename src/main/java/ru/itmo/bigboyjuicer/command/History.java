package ru.itmo.bigboyjuicer.command;

import java.util.List;

public class History extends AbstractCommand {

    public History() {
        super("history");
    }

    @Override
    public List<String> execute() {
        return null;
    }
}
