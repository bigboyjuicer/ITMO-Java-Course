package command;

import entity.SpaceMarine;
import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
/***
 * Class that realize behaviour of "filter_by_weapon_type" command.
 * Filters the collection by the weaponType field.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class FilterByWeaponType implements Executable, Validatable {

  private String arg;
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    Set<SpaceMarine> set =
        spaceMarines.getSet().stream()
            .filter(o -> o.getWeaponType().toString().toLowerCase().equals(this.arg))
            .collect(Collectors.toSet());
    if (set.isEmpty()) {
      System.out.println("В коллекции не нашлось записей с таким типом оружия");
    } else {
      set.forEach(System.out::println);
    }
    CommandManager.executed = true;
    CommandManager.HISTORY.add("filter_by_weapon_type");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    setArg(scanner);
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("filter_by_weapon_type") && this.arg != null) {
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
