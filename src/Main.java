import entity.SpaceMarine;
import entity.SpaceMarineSet;
import manager.CommandManager;
import manager.FileManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    /*public static final Map<String, Runnable> commands = new HashMap<>() {
        {
            put("help", CommandManager::help);
            put("info", CommandManager::info);
        }
    };*/

    public static void main(String[] args) {
        SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag) {
            switch (scanner.next().toLowerCase()){
                case("help"):
                    CommandManager.help();
                    break;
                case("info"):
                    CommandManager.info(spaceMarines);
                    break;
                case("show"):
                    CommandManager.show(spaceMarines);
                    break;
                case("add"):
                    CommandManager.add(spaceMarines);
                    break;
                case("update"):
                    CommandManager.update(scanner.nextInt(), spaceMarines);
                    break;
                case("remove_by_id"):
                    CommandManager.remove(scanner.nextInt(), spaceMarines);
                    break;
                case("clear"):
                    CommandManager.clear(spaceMarines);
                    break;
                case("save"):
                    CommandManager.save(spaceMarines);
                    break;
                case("execute_script"):
                    CommandManager.executeScript(scanner.nextLine().trim(), spaceMarines);
                    break;
                case("exit"):
                    flag = false;
                    break;
                case("add_if_min"):
                    CommandManager.addIfMin(spaceMarines);
                    break;
                case("remove_greater"):
                    CommandManager.removeGreater(spaceMarines);
                    break;
                case("max_by_creation_date"):
                    CommandManager.maxByCreationDate(spaceMarines);
                    break;
                case("filter_by_weapon_type"):
                    CommandManager.filterByWeaponType(scanner.next(), spaceMarines);
                    break;
                case("print_ascending"):
                    CommandManager.printAscending(spaceMarines);
                    break;
                default:
                    System.out.println("""
                            Неверная команда.
                            help : вывести справку по доступным командам.
                            """);
                    break;
            }
        }


    }
}