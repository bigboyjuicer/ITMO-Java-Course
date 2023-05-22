package ru.itmo.bigboyjuicer.command;

import java.io.Serializable;
import java.util.List;

public class Help extends AbstractCommand implements Serializable {

  public Help() {
    super("help");
  }

  @Override
  public List<String> execute() {
    return List.of(
        """
                        \t\tСправка по доступным командам

                        help : вывести справку по доступным командам
                        info : вывести информацию о коллекции
                        show : вывести все элементы коллекции
                        add : добавить новый элемент в коллекцию
                        update id : обновить значение элемента коллекции
                        remove_by_id id : удалить элемент из коллекции
                        clear : очистить коллекцию
                        execute_script file_name : считать и исполнить скрипт из указанного файла
                        exit : завершить программу
                        add_if_min : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                        remove_greater : удалить из коллекции все элементы, превышающие заданный
                        history : вывести последние 7 команд
                        max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным
                        filter_by_weapon_type weaponType : вывести элементы, значение поля weaponType которых равно заданному
                        print_ascending : вывести элементы коллекции в порядке возрастания

                        """);
  }
}
