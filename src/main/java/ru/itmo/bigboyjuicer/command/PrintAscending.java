package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.Serializable;
import java.util.List;

public class PrintAscending extends AbstractCommand implements Serializable {

    public PrintAscending() {
        super("print_ascending");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        return spaceMarines.getSet().stream().sorted().map(SpaceMarine::toString).toList();
    }
}
