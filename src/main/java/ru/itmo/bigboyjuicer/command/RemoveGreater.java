package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class RemoveGreater extends AbstractCommand implements Serializable {

  public RemoveGreater() {
    super("remove_greater");
  }

  @Override
  public List<String> execute() {
    Server.spaceMarineSet.removeIf(getElement());
    return List.of("Records have been successfully deleted");
  }
}
