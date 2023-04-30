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
public class ExecuteScript implements Validatable {
  private String arg;
  private SpaceMarineSet spaceMarines;
  private List<String> files = new ArrayList<>();

  public void execute(List<String> commands) {
    for(String command : commands) {
      CommandManager.menu(command, this.spaceMarines);
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
      if(files.contains(arg)) {
        System.out.println("Скрипт уже был выполнен либо все еще выполняется");
        CommandManager.executed = true;
        return true;
      } else {
        files.add(arg);
        this.spaceMarines = spaceMarines;
        fillCommandsFromScript();
        return true;
      }
    }
    return false;
  }

  private void fillCommandsFromScript() {
    try (Scanner scanner = new Scanner(new FileReader(this.arg))) {
      List<String> commands = new ArrayList<>();
      while (scanner.hasNextLine()) {
        commands.add(scanner.nextLine());
      }
      execute(commands);
    } catch (FileNotFoundException e) {
      System.out.println("Введенный файл не существует или не доступен для чтения");
      CommandManager.executed = true;
      CommandManager.HISTORY.add("execute_script");
    }
  }

  private void setArg(Scanner scanner) {
    this.arg = scanner.hasNext() ? scanner.next() : null;
  }
}
