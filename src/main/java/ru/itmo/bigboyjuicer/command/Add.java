package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.Serializable;
import java.util.List;

public class Add extends AbstractCommand implements Serializable {

    public Add() {
        super("add");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        spaceMarines.add(getElement());
        return List.of("The element was successfully added");
    }

    @Override
    public void setArg(String arg) {
        super.setArg(arg);
    }
}
