package ru.itmo.bigboyjuicer.entity;

import java.util.*;

/***
 * Class that manages collection of the SpaceMarine objects.
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class SpaceMarineSet {
  private LinkedHashSet<SpaceMarine> spaceMarines;

  public SpaceMarineSet(LinkedHashSet<SpaceMarine> set) {
    spaceMarines = set == null ? new LinkedHashSet<>() : set;
  }

  public void add(SpaceMarine spaceMarine) {
    spaceMarines.add(spaceMarine);
  }

  public void delete(long id) {
    spaceMarines.removeIf(o -> o.getId() == id);
  }

  public void update(SpaceMarine spaceMarine) {
    spaceMarines.removeIf(o -> o.getId() == spaceMarine.getId());
    spaceMarines.add(spaceMarine);
  }

  public void removeIf(SpaceMarine spaceMarine) {
    spaceMarines.removeIf(o -> o.getName().compareTo(spaceMarine.getName()) > 0);
  }

  public SpaceMarine getElement(long id) {
    Iterator<SpaceMarine> iterator = spaceMarines.iterator();
    while (iterator.hasNext()) {
      SpaceMarine spaceMarine = iterator.next();
      if (spaceMarine.getId() == id) {
        return spaceMarine;
      }
    }
    return null;
  }

  public void clear() {
    spaceMarines.clear();
  }

  public LinkedHashSet<SpaceMarine> getSet() {
    return spaceMarines;
  }
}
