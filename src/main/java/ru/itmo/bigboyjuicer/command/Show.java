package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.server.Server;
import ru.itmo.bigboyjuicer.server.entity.SpaceMarine;

import java.io.Serializable;
import java.util.List;

public class Show extends AbstractCommand implements Serializable {
    public Show() {
        super("show");
    }

    @Override
    public List<String> execute() {
        return Server.spaceMarineSet.getSet().stream().sorted().map(SpaceMarine::toString).toList();
    }
}
