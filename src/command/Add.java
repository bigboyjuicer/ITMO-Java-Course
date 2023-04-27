package command;

import builder.EntityBuilder;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.LoggerManager;

import java.util.Scanner;

/***
 * Class that realize behaviour of "add" command.
 * It provides the user with input for each field of the SpaceMarine entity.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Add implements Executable, Validatable {
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    this.spaceMarines.add(EntityBuilder.spaceMarineBuilder());
    CommandManager.executed = true;
    CommandManager.HISTORY.add("add");
    System.out.println("Запись успешно добавлена");
    LoggerManager.logInfo("Added new element");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("add")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
