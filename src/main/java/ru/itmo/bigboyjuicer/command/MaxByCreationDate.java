package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

public class MaxByCreationDate extends AbstractCommand implements Serializable {

  public MaxByCreationDate() {
    super("max_by_creation_date");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    if (spaceMarines.getSet().stream()
        .max(Comparator.comparing(SpaceMarine::getCreationDate))
        .isPresent()) {
      SpaceMarine spaceMarine =
              spaceMarines.getSet().stream()
              .max(Comparator.comparing(SpaceMarine::getCreationDate))
              .get();
      return List.of(spaceMarine.toString());
    }

    return List.of("The collection is empty");
  }
}
