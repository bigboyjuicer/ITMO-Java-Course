package command;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "add_if_min" command.
 * It provides to add entity if its value less than that of the smallest element in that collection.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class AddIfMin implements Executable, Validatable {

  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    SpaceMarine spaceMarine = EntityBuilder.spaceMarineBuilder();
    SpaceMarine minSpaceMarine =
        this.spaceMarines.getSet().stream().min(SpaceMarine::compareTo).orElse(null);
    if (minSpaceMarine != null && minSpaceMarine.compareTo(spaceMarine) > 0) {
      this.spaceMarines.add(spaceMarine);
      System.out.println("Запись успешно добавлена");
    } else {
      System.out.println("Запись не удовлетворяет условиям");
    }

    CommandManager.executed = true;
    CommandManager.HISTORY.add("add_if_min");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("add_if_min")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
