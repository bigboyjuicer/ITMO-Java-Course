package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "show" command.
 * Shows all elements in the collection.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Show implements Executable, Validatable {
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    if (this.spaceMarines.getSet().isEmpty()) System.out.println("Коллекция пуста");
    else this.spaceMarines.getSet().forEach(System.out::println);
    CommandManager.executed = true;
    CommandManager.HISTORY.add("show");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("show")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
