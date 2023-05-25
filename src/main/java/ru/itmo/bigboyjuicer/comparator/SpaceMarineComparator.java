package ru.itmo.bigboyjuicer.comparator;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;

import java.io.Serializable;
import java.util.Comparator;

public class SpaceMarineComparator implements Comparator<SpaceMarine>, Serializable {

    @Override
    public int compare(SpaceMarine o1, SpaceMarine o2) {
        return o1.getCoordinates().compareTo(o2.getCoordinates());
    }
}
