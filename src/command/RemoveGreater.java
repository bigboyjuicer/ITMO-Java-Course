package command;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;

/***
 * Class that realize behaviour of "remove_greater" command.
 * Removes all elements from the collection that exceed the specified.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class RemoveGreater implements Executable, Validatable {

  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    SpaceMarine spaceMarine = EntityBuilder.spaceMarineBuilder();
    this.spaceMarines.removeIf(spaceMarine);
    System.out.println("Записи успешно удалены");
    CommandManager.executed = true;
    CommandManager.HISTORY.add("remove_greater");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("remove_greater")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
