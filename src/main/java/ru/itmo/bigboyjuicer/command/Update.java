package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.builder.EntityBuilder;
import ru.itmo.bigboyjuicer.server.Server;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;

import java.io.Serializable;
import java.util.List;

public class Update extends AbstractCommand implements Serializable {

  private SpaceMarine newSpaceMarine;

  public Update() {
    super("update");
  }

  @Override
  public List<String> execute() {
    try {
      SpaceMarine spaceMarine = Server.spaceMarineSet.getElement(Long.parseLong(getArg()));
      if (spaceMarine != null) {
        newSpaceMarine.setId(spaceMarine.getId());
        newSpaceMarine.setCreationDate(spaceMarine.getCreationDate());
        Server.spaceMarineSet.update(newSpaceMarine);

        return List.of("Success");

        // LoggerManager.logInfo("Updated element with id: " + this.arg);

      }
    } catch (NumberFormatException ignored) {

    }
    return List.of("The element with this id doesn't exist");
  }

  @Override
  public void setArg(String arg) {
    super.setArg(arg);
    newSpaceMarine = EntityBuilder.spaceMarineBuilder();
  }
}
