package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class Clear extends AbstractCommand implements Serializable {

    public Clear() {
        super("clear");
    }

    @Override
    public List<String> execute() {
        Server.spaceMarineSet.clear();
        return List.of("Success");
    }
}
