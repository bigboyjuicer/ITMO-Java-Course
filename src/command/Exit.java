package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "exit" command.
 * Suspends the programme.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Exit implements Executable, Validatable {

  @Override
  public void execute() {
    CommandManager.active = false;
    CommandManager.executed = true;
    CommandManager.HISTORY.add("exit");
    System.out.println("Завершение программы...");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("exit")) {
      execute();
      return true;
    }
    return false;
  }
}
