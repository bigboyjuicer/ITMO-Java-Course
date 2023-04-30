package command;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;
import manager.FileManager;

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
    if (aCommand.equals("remove_greater")) {
      if(scanner.hasNextLine()) {
        if(validateArg(scanner.nextLine(), spaceMarines)) {
          System.out.println("Записи успешно удалены");
        }
        CommandManager.executed = true;
        CommandManager.HISTORY.add("remove_greater");
        return true;
      }
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }

  private boolean validateArg(String arg, SpaceMarineSet spaceMarines) {
    SpaceMarine spaceMarine = FileManager.parseJson(arg);
    if(spaceMarine != null) {
      spaceMarines.removeIf(spaceMarine);
      return true;
    }
    return false;
  }
}
