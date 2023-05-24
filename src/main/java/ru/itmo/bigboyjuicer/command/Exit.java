package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.util.List;

public class Exit extends AbstractCommand{

    public Exit() {
        super("exit");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        return null;
    }
}
