package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class RemoveById extends AbstractCommand implements Serializable {

  public RemoveById() {
    super("remove_by_id");
  }

  @Override
  public List<String> execute() {
    try {
      if (Server.spaceMarineSet.getElement(Long.parseLong(getArg())) != null) {
        Server.spaceMarineSet.delete(Long.parseLong(getArg()));

        return List.of("Success");

        // LoggerManager.logInfo("Removed element with id: " + this.arg);

      }
    } catch (NumberFormatException ignored) {

    }
    return List.of("The element with this id doesn't exist");
  }
}
