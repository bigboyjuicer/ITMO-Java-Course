package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.Serializable;
import java.util.List;

public class Clear extends AbstractCommand implements Serializable {

    public Clear() {
        super("clear");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        spaceMarines.clear();
        return List.of("The collection was successfully cleared");
    }
}
