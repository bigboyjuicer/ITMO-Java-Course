package entity;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class SpaceMarineSet {
    private final LinkedHashSet<SpaceMarine> spaceMarines;

    public SpaceMarineSet(LinkedHashSet<SpaceMarine> set) {
        this.spaceMarines = set;
    }

    public void add(SpaceMarine spaceMarine) {
        spaceMarines.add(spaceMarine);
    }

    public void delete(int id) {
        spaceMarines.removeIf(o -> o.getId() == id);
    }

    public void edit(int id, SpaceMarine spaceMarine) {

    }

    public void clear() {
        spaceMarines.clear();
    }

    public Set<SpaceMarine> get() {
        return Set.copyOf(spaceMarines);
    }
}
