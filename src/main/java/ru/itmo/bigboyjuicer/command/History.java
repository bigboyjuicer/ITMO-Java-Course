package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.util.List;

public class History extends AbstractCommand {

    public History() {
        super("history");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        return null;
    }
}
