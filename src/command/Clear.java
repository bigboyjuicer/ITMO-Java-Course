package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "clear" command.
 * It clears the collection.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Clear implements Executable, Validatable {
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    this.spaceMarines.clear();
    System.out.println("Коллекция успешно было отчищена");
    CommandManager.executed = true;
    CommandManager.HISTORY.add("clear");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("clear")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
