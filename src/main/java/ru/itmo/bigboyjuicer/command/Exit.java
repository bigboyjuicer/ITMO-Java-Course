package ru.itmo.bigboyjuicer.command;

import java.util.List;

public class Exit extends AbstractCommand{

    public Exit() {
        super("exit");
    }

    @Override
    public List<String> execute() {
        return null;
    }
}
