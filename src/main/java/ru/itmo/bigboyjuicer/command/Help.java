package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.Serializable;
import java.util.List;

public class Help extends AbstractCommand implements Serializable {

  public Help() {
    super("help");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    return List.of(
        """
                        \t\tAvailable commands

                        help : display available commands
                        info : display information about the collection
                        show : display all element in the collection
                        add : add a new element to the collection
                        update id : update element of the collection
                        remove_by_id id : delete element from the collection
                        clear : clear the collection
                        execute_script file_name : read and execute a script from a specified file
                        exit : exit from program
                        add_if_min : add a new element to the collection if its value is smaller than the smallest element in that collection
                        remove_greater : remove all elements from the collection that exceed the specified
                        history : display the last 7 commands
                        max_by_creation_date : output any element in the collection whose "creationDate" field value is the maximum
                        filter_by_weapon_type weaponType : display elements with a "weaponType" field value equal to the given one
                        print_ascending : display th elements of the collection in ascending order

                        """);
  }
}
