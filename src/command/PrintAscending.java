package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;

/***
 * Class that realize behaviour of "print_ascending" command.
 * Prints the elements of the collection in ascending order.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class PrintAscending implements Executable, Validatable {

  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    spaceMarines.getSet().stream().sorted().forEach(System.out::println);
    CommandManager.executed = true;
    CommandManager.HISTORY.add("print_ascending");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("print_ascending")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
