package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;

public class RemoveGreater extends AbstractCommand implements Serializable {

  public RemoveGreater() {
    super("remove_greater");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    spaceMarines.removeIf(getElement());
    return List.of("Elements were successfully removed");
  }
}
