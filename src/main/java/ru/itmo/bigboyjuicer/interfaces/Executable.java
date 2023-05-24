package ru.itmo.bigboyjuicer.interfaces;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.util.List;

public interface Executable {
    List<String> execute(SpaceMarineSet spaceMarines);
}
