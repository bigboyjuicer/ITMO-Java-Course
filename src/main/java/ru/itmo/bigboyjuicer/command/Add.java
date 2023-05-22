package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.builder.EntityBuilder;
import ru.itmo.bigboyjuicer.server.Server;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;

import java.io.Serializable;
import java.util.List;

public class Add extends AbstractCommand implements Serializable {

    public Add() {
        super("add");
    }

    @Override
    public List<String> execute() {
        Server.spaceMarineSet.add(getElement());
        return List.of("Success");
    }

    @Override
    public void setArg(String arg) {
        super.setArg(arg);
    }
}
