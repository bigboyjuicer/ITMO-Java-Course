package command;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.LoggerManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/***
 * Class that realize behaviour of "update" command.
 * Updates element in the collection by its id.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Update implements Executable, Validatable {

  private long arg;
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    SpaceMarine spaceMarine = this.spaceMarines.getElement(this.arg);
    if (spaceMarine != null) {
      SpaceMarine spaceMarineUpdated = EntityBuilder.spaceMarineBuilder();
      spaceMarineUpdated.setId(spaceMarine.getId());
      spaceMarineUpdated.setCreationDate(spaceMarine.getCreationDate());
      this.spaceMarines.update(spaceMarineUpdated);

      System.out.println("Запись успешно обновлена");

      LoggerManager.logInfo("Updated element with id: " + this.arg);

    } else System.out.println("Элемента с таким id нет в коллекции");

    CommandManager.executed = true;
    CommandManager.HISTORY.add("update");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    try {
      Scanner scanner = new Scanner(command);
      String aCommand = scanner.next();
      if (aCommand.equals("update")) {
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
