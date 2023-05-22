package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class AddIfMin extends AbstractCommand implements Serializable {

  public AddIfMin() {
    super("add_if_min");
  }

  @Override
  public List<String> execute() {
    if (checkIfMin()) {
      Server.spaceMarineSet.add(getElement());
      return List.of("Success");
    } else {
      return List.of("The record does not meet the conditions");
    }
  }

  private boolean checkIfMin() {
    SpaceMarine minSpaceMarine =
        Server.spaceMarineSet.getSet().stream().min(SpaceMarine::compareTo).orElse(null);
    return minSpaceMarine != null && minSpaceMarine.compareTo(getElement()) > 0;
  }
}
