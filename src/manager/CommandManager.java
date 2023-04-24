package manager;

import builder.EntityBuilder;
import entity.SpaceMarine;
import entity.SpaceMarineSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class CommandManager {

    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Scanner scanner = new Scanner(System.in);

    public static void help() {
        System.out.println("""
                Справка по доступным командам
                                
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
    }

    public static void info(SpaceMarineSet spaceMarineSet) {
        try {
            String type = "SpaceMarine";
            File file = new File("data/space_marine.json");
            float usableSpace = (float) file.length() / 1000;
            FileTime creationTime = (FileTime) Files.getAttribute(Path.of("data/space_marine.json"), "creationTime");
            int size = spaceMarineSet.getSet().size();

            System.out.printf("""
                    Тип коллекции: %s
                    Дата инициализации: %s
                    Занимаемое место: %.1f КБ
                    Кол-во элементов: %d
                    """, type, creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormat), usableSpace, size);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(SpaceMarineSet spaceMarineSet) {
        if (spaceMarineSet.getSet().isEmpty()) System.out.println("Коллекция пуста");
        else spaceMarineSet.getSet().forEach(System.out::println);
    }

    public static void add(SpaceMarineSet spaceMarines) {
        spaceMarines.add(EntityBuilder.spaceMarineBuilder());
    }

    public static void update(int id, SpaceMarineSet spaceMarineSet) {
        SpaceMarine spaceMarine = spaceMarineSet.getElement(id);
        if(spaceMarine != null) {
            SpaceMarine spaceMarineUpdated = EntityBuilder.spaceMarineBuilder();
            spaceMarineUpdated.setId(spaceMarine.getId());
            spaceMarineUpdated.setCreationDate(spaceMarine.getCreationDate());
            spaceMarineSet.update(spaceMarineUpdated);
        } else System.out.println("Элемента с таким id нет в коллекции");
    }

    public static void remove(int id, SpaceMarineSet spaceMarines) {
        if(spaceMarines.getElement(id) != null) {
            spaceMarines.delete(id);
        } else System.out.println("Элемента с таким id нет в коллекции");
    }

    public static void clear(SpaceMarineSet spaceMarines) {
        spaceMarines.clear();
    }

    public static void save(SpaceMarineSet spaceMarines) {
        FileManager.save(spaceMarines);
    }

    public static void executeScript(String file, SpaceMarineSet spaceMarines){
        try(Scanner scanner = new Scanner(new FileReader(file))){

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addIfMin(SpaceMarineSet spaceMarines) {
        SpaceMarine spaceMarine = EntityBuilder.spaceMarineBuilder();
        SpaceMarine minSpaceMarine = spaceMarines.getSet().stream().min(SpaceMarine::compareTo).orElse(null);
        if(minSpaceMarine != null && minSpaceMarine.compareTo(spaceMarine) > 0) {
            spaceMarines.add(spaceMarine);
        }
    }

    public static void removeGreater(SpaceMarineSet spaceMarines) {
        SpaceMarine spaceMarine = EntityBuilder.spaceMarineBuilder();
        spaceMarines.removeIf(spaceMarine);
    }

    public static void maxByCreationDate(SpaceMarineSet spaceMarines) {
        Optional<SpaceMarine> spaceMarine = spaceMarines.getSet().stream().max(Comparator.comparing(SpaceMarine::getCreationDate));
        System.out.println(spaceMarine);
    }

    public static void filterByWeaponType(String weaponType, SpaceMarineSet spaceMarines) {
        LinkedHashSet<SpaceMarine> set = spaceMarines.getSet().stream().filter(o -> o.getWeaponType().toString().equals(weaponType)).collect(Collectors.toCollection(LinkedHashSet::new));
        set.forEach(System.out::println);
    }

    public static void printAscending(SpaceMarineSet spaceMarines) {
        spaceMarines.getSet().stream().sorted().forEach(System.out::println);
    }

}
