package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.LoggerManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * Class that realize behaviour of "remove_by_id" command.
 * Removes an element from collection by its id.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class RemoveById implements Executable, Validatable {
  private long arg;
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    if (this.spaceMarines.getElement(this.arg) != null) {
      this.spaceMarines.delete(this.arg);

      System.out.println("Запись успешно удалена");

      LoggerManager.logInfo("Removed element with id: " + this.arg);

    } else System.out.println("Элемента с таким id нет в коллекции");

    CommandManager.executed = true;
    CommandManager.HISTORY.add("remove_by_id");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    try {
      Scanner scanner = new Scanner(command);
      String aCommand = scanner.next();
      if (aCommand.equals("remove_by_id")) {
        setArg(scanner);
        if (scanner.hasNext()) {
          return false;
        }
        if (this.arg != 0) {
          this.spaceMarines = spaceMarines;
          execute();
          return true;
        }
      }
    } catch (InputMismatchException ex) {
      System.out.println("Был введен неверный аргумент. Предполагалось число.");
      CommandManager.executed = true;
    }
    return false;
  }

  private void setArg(Scanner scanner) {
    this.arg = scanner.hasNext() ? scanner.nextLong() : 0;
  }
}
