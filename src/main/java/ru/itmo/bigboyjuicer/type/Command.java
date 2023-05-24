package ru.itmo.bigboyjuicer.type;

import ru.itmo.bigboyjuicer.command.*;

public enum Command {
  HELP(new Help(), false),
  INFO(new Info(), false),
  SHOW(new Show(), false),
  ADD(new Add(), false),
  UPDATE(new Update(), true),
  REMOVE_BY_ID(new RemoveById(), true),
  CLEAR(new Clear(), false),
  EXECUTE_SCRIPT(new ExecuteScript(), true),
  EXIT(new Exit(), false),
  ADD_IF_MIN(new AddIfMin(), false),
  REMOVE_GREATER(new RemoveGreater(), false),
  HISTORY(new History(), false),
  MAX_BY_CREATION_DATE(new MaxByCreationDate(), false),
  FILTER_BY_WEAPON_TYPE(new FilterByWeaponType(), true);

  private AbstractCommand command;
  private boolean containsArg;

  Command(AbstractCommand command, boolean containsArg) {
    this.command = command;
    this.containsArg = containsArg;
  }

  public AbstractCommand getCommand() {
    return command;
  }

  public void setCommand(AbstractCommand command) {
    this.command = command;
  }

  public boolean isContainsArg() {
    return containsArg;
  }

  public void setContainsArg(boolean containsArg) {
    this.containsArg = containsArg;
  }
}
