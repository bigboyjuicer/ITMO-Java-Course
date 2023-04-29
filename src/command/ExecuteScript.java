package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/***
 * Class that realize behaviour of "execute_script" command.
 * It provides execution of a script that is written in the file.
 *
 * @author Maksim Zinin
 * @version ver 1.1
 */
public class ExecuteScript implements Executable, Validatable {
  private String arg;
  private SpaceMarineSet spaceMarines;
  private Iterator<String> iterator;

  @Override
  public void execute() {
    while (iterator.hasNext()) {
      CommandManager.menu(iterator.next(), this.spaceMarines);
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
      fillCommandsFromScript();
      return true;
    }
    return false;
  }

  private void fillCommandsFromScript() {
    if (iterator == null) {
      try (Scanner scanner = new Scanner(new FileReader(this.arg))) {
        List<String> commands = new ArrayList<>();
        while (scanner.hasNextLine()) {
          commands.add(scanner.nextLine());
          iterator = commands.iterator();
        }
        execute();
      } catch (FileNotFoundException e) {
        System.out.println("Введенный файл не существует или не доступен для чтения");
        CommandManager.executed = true;
        CommandManager.HISTORY.add("execute_script");
      }
    } else {
      execute();
    }
  }

  private void setArg(Scanner scanner) {
    this.arg = scanner.hasNext() ? scanner.next() : null;
  }
}
