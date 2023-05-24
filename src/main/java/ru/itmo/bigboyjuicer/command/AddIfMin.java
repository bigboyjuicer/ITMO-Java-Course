package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.Serializable;
import java.util.List;

public class AddIfMin extends AbstractCommand implements Serializable {

  public AddIfMin() {
    super("add_if_min");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    if (checkIfMin(spaceMarines)) {
      spaceMarines.add(getElement());
      return List.of("The element was successfully added");
    } else {
      return List.of("The element does not meet the conditions");
    }
  }

  private boolean checkIfMin(SpaceMarineSet spaceMarines) {
    SpaceMarine minSpaceMarine =
        spaceMarines.getSet().stream().min(SpaceMarine::compareTo).orElse(null);
    return minSpaceMarine != null && minSpaceMarine.compareTo(getElement()) > 0;
  }
}
