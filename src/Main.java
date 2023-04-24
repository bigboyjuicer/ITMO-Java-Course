import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import entity.Chapter;
import entity.Coordinates;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import manager.CommandManager;
import manager.SpaceMarineManager;
import type.AstartesCategory;
import type.Weapon;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    /*public static final Map<String, Runnable> commands = new HashMap<>() {
        {
            put("help", CommandManager::help);
            put("info", CommandManager::info);
        }
    };*/

    public static void main(String[] args) {

        /*SpaceMarine spaceMarine = new SpaceMarine(1, "SpaceMarine", new Coordinates(0, 0), 100, 2, AstartesCategory.LIBRARIAN, Weapon.HEAVY_BOLTGUN, new Chapter("Chapter", "ParentLegion", 50, "World1"));
        SpaceMarine spaceMarine1 = new SpaceMarine(2, "SpaceMarine2", new Coordinates(5, 5), 100, 2, AstartesCategory.AGGRESSOR, Weapon.INFERNO_PISTOL, new Chapter("Chapter2", "ParentLegion2", 50, "World2"));

        List<SpaceMarine> list = new ArrayList<>();
        list.add(spaceMarine);
        list.add(spaceMarine1);*/

        SpaceMarineSet spaceMarines = new SpaceMarineSet(SpaceMarineManager.selectAll());

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
                case("remove_by_id"):
                    CommandManager.remove(scanner.nextInt(), spaceMarines);
                    break;
                case("clear"):
                    CommandManager.clear(spaceMarines);
                    break;
                case("max_by_creation_date"):
                    CommandManager.maxByCreationDate(spaceMarines);
                    break;
                case("filter_by_weapon_type"):
                    CommandManager.filterByWeaponType(scanner.next(), spaceMarines);
                    break;
                case("exit"):
                    flag = false;
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