package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
/***
 * Class that realize behaviour of "execute_script" command.
 * It provides execution of a script that is written in the file.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class ExecuteScript implements Executable, Validatable {

  private String arg;
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    try (Scanner scanner = new Scanner(new FileReader(this.arg))) {
      CommandManager.menu(scanner.nextLine().toLowerCase().trim(), this.spaceMarines);
    } catch (FileNotFoundException e) {
      System.out.println("Введенный файл не существует");
    }

    CommandManager.executed = true;
    CommandManager.HISTORY.add("execute_script");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    setArg(scanner);
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("execute_script") && this.arg != null) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }

  private void setArg(Scanner scanner) {
    this.arg = scanner.hasNext() ? scanner.next() : null;
  }
}
