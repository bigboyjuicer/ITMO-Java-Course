package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class RemoveById extends AbstractCommand implements Serializable {

  public RemoveById() {
    super("remove_by_id");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    try {
      if (spaceMarines.getElement(Long.parseLong(getArg())) != null) {
        spaceMarines.delete(Long.parseLong(getArg()));

        return List.of("The element was successfully removed");

        // LoggerManager.logInfo("Removed element with id: " + this.arg);

      }
    } catch (NumberFormatException ignored) {

    }
    return List.of("The element with this id doesn't exist");
  }
}
