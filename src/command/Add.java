package command;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.FileManager;
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
    if (aCommand.equals("add")) {
      if(scanner.hasNextLine()) {
        if(validateArg(scanner.nextLine(), spaceMarines)) {
          System.out.println("Запись успешно добавлена");
        }
        CommandManager.executed = true;
        CommandManager.HISTORY.add("add");
        return true;
      }
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }

  private boolean validateArg(String arg, SpaceMarineSet spaceMarines) {
    SpaceMarine spaceMarine = FileManager.parseJson(arg);
    if(spaceMarine != null) {
      spaceMarines.add(spaceMarine);
      return true;
    }
    return false;
  }
}
