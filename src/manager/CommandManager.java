package manager;

import command.*;
import entity.SpaceMarineSet;
import interfaces.Validatable;

import java.util.*;

/***
 * Class that manages the execution of commands
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class CommandManager {
  private static final List<Validatable> COMMANDS = new ArrayList<>();
  public static final Queue<String> HISTORY = new ArrayDeque<>(7);

  public static boolean active = true;
  public static boolean executed = false;

  /**
   * Method that fills in collection all available commands
   */
  private static void commandsFill() {
    COMMANDS.add(new Help());
    COMMANDS.add(new Info());
    COMMANDS.add(new Show());
    COMMANDS.add(new Add());
    COMMANDS.add(new Update());
    COMMANDS.add(new RemoveById());
    COMMANDS.add(new Clear());
    COMMANDS.add(new Save());
    COMMANDS.add(new ExecuteScript());
    COMMANDS.add(new Exit());
    COMMANDS.add(new AddIfMin());
    COMMANDS.add(new RemoveGreater());
    COMMANDS.add(new History());
    COMMANDS.add(new MaxByCreationDate());
    COMMANDS.add(new FilterByWeaponType());
    COMMANDS.add(new PrintAscending());
  }

  /**
   * Method that provides menu function
   * It creates iterator to go through the all available commands
   * and calls validate() method of each one element of the iterator
   * @param command
   * @param spaceMarines
   */
  public static void menu(String command, SpaceMarineSet spaceMarines) {
    commandsFill();
    Iterator<Validatable> iterator = COMMANDS.iterator();
    while (!executed) {
      if (!iterator.hasNext()) {
        System.out.println(
                            """
                            Неверная команда.
                            help : вывести справку по доступным командам.
                            """);
        break;
      }
      iterator.next().validate(command, spaceMarines);
    }
    executed = false;
  }
}
