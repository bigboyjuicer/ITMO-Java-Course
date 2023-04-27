package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.util.Scanner;
/***
 * Class that realize behaviour of "help" command.
 * Output menu that provides available commands.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Help implements Executable, Validatable {

  @Override
  public void execute() {
    System.out.println(
        """
                \t\tСправка по доступным командам

                help : вывести справку по доступным командам
                info : вывести информацию о коллекции
                show : вывести все элементы коллекции
                add : добавить новый элемент в коллекцию
                update id : обновить значение элемента коллекции
                remove_by_id id : удалить элемент из коллекции
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла
                exit : завершить программу
                add_if_min : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                remove_greater : удалить из коллекции все элементы, превышающие заданный
                history : вывести последние 7 команд
                max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным
                filter_by_weapon_type weaponType : вывести элементы, значение поля weaponType которых равно заданному
                print_ascending : вывести элементы коллекции в порядке возрастания

                """);

    CommandManager.executed = true;
    CommandManager.HISTORY.add("help");
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("help")) {
      execute();
      return true;
    }
    return false;
  }
}
