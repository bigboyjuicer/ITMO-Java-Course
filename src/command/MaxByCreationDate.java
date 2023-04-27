package command;

import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Comparator;
import java.util.Scanner;

/***
 * Class that realize behaviour of "max_by_creation_date" command.
 * Outputs the most recently created element.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class MaxByCreationDate implements Executable, Validatable {

  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    if(spaceMarines.getSet().stream().max(Comparator.comparing(SpaceMarine::getCreationDate)).isPresent()) {
      SpaceMarine spaceMarine = spaceMarines.getSet().stream().max(Comparator.comparing(SpaceMarine::getCreationDate)).get();
      System.out.println(spaceMarine);
    }
    CommandManager.executed = true;
    CommandManager.HISTORY.add("max_by_creation_date");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("max_by_creation_date")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
