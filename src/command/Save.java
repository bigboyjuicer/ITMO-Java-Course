package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.FileManager;
import manager.LoggerManager;

import java.util.Scanner;

/***
 * Class that realize behaviour of "save" command.
 * Saves the collection to a file.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Save implements Executable, Validatable {

  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    FileManager.save(this.spaceMarines);
    System.out.println("Коллекция успешно было сохранена");
    LoggerManager.logInfo("Saved collection");
    CommandManager.executed = true;
    CommandManager.HISTORY.add("save");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("save")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
