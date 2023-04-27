package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "history" command.
 * Outputs the last 7 commands entered by the user.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class History implements Executable, Validatable {

  @Override
  public void execute() {
    if (CommandManager.HISTORY.size() == 0) {
      System.out.println("История введенных команд пуста");
    } else {
      System.out.println("История введенных команд");
      CommandManager.HISTORY.forEach(System.out::println);
    }

    CommandManager.executed = true;
    CommandManager.HISTORY.add("history");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("history")) {
      execute();
      return true;
    }
    return false;
  }
}
