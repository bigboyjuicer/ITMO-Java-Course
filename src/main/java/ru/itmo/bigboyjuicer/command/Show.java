package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.server.Server;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;

import java.io.Serializable;
import java.util.List;

public class Show extends AbstractCommand implements Serializable {
    public Show() {
        super("show");
    }

    @Override
    public List<String> execute() {
        if(Server.spaceMarineSet.getSet().isEmpty()) return List.of("Collection is empty");
        else return Server.spaceMarineSet.getSet().stream().sorted().map(SpaceMarine::toString).toList();
    }
}
