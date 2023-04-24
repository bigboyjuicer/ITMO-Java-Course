package manager;

import entity.Chapter;
import entity.Coordinates;
import entity.SpaceMarine;
import entity.SpaceMarineSet;
import type.AstartesCategory;
import type.Weapon;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
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
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции
                remove_by_id id : удалить элемент из коллекции
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла
                exit : завершить программу
                add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
                remove_greater {element} : удалить из коллекции все элементы, превышающие заданный
                history : вывести последние 7 команд
                max_by_creation_date : вывести любой объект из коллекции, значение поля creationDate которого является максимальным
                filter_by_weapon_type weaponType : вывести элементы, значение поля weaponType которых равно заданному
                print_ascending : вывести элементы коллекции в порядке возрастания
                
                """);
    }

    public static void info(SpaceMarineSet spaceMarineSet) {
        try {
            String type = "SpaceMarine";
            FileTime creationTime = (FileTime) Files.getAttribute(Path.of("data/space_marine.json"), "creationTime");
            int size = spaceMarineSet.get().size();

            System.out.printf("""
                    Тип коллекции: %s
                    Дата инициализации: %s
                    Кол-во элементов: %d
                    Поля элементов:{id,name,coordinates:{x,y},creationDate,health,category,weaponType,chapter:{name,parentLegion,marinesCount,world}}
                    """, type, creationTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(dateTimeFormat), size);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void show(SpaceMarineSet spaceMarineSet) {
        if(spaceMarineSet.get().isEmpty()) System.out.println("Коллекция пуста");
        else spaceMarineSet.get().forEach(System.out::println);
    }

    public static void add(SpaceMarineSet spaceMarines) {
        System.out.println("Введите название");
        String name = scanner.nextLine().trim();
        System.out.println("    Введите координаты");
        System.out.println("Введите x");
        int x = scanner.nextInt();
        System.out.println("Введите y");
        int y = scanner.nextInt();
        System.out.println("Введите здоровье");
        float health = scanner.nextFloat();
        System.out.println("Введите кол-во жизней");
        int heartCounter = scanner.nextInt();
        System.out.println("    Выберите категорию (число)");
        System.out.println("1 - AGGRESSOR, 2 - LIBRARIAN, 3 - CHAPLAIN");
        AstartesCategory category = switch (scanner.nextInt()) {
            case (1) -> AstartesCategory.AGGRESSOR;
            case (2) -> AstartesCategory.LIBRARIAN;
            case (3) -> AstartesCategory.CHAPLAIN;
            default -> null;
        };
        System.out.println("    Выберите тип оружия (число)");
        System.out.println("1 - HEAVY_BOLTGUN, 2 - BOLT_PISTOL, 3 - BOLT_RIFLE, 4 - INFERNO_PISTOL");
        Weapon weapon = switch (scanner.nextInt()) {
            case (1) -> Weapon.HEAVY_BOLTGUN;
            case (2) -> Weapon.BOLT_PISTOL;
            case (3) -> Weapon.BOLT_RIFLE;
            case (4) -> Weapon.INFERNO_PISTOL;
            default -> null;
        };
        System.out.println("    Введите chapter");
        System.out.println("Введите название");
        String chapterName = scanner.nextLine().trim();
        System.out.println("Введите parentLegion");
        String parentLegion = scanner.nextLine().trim();
        System.out.println("Введите кол-во кораблей");
        long marinesCount = scanner.nextLong();
        System.out.println("Введите мир");
        String world = scanner.nextLine();

        SpaceMarine spaceMarine = new SpaceMarine(name, new Coordinates(x, y), health, heartCounter, category, weapon, new Chapter(chapterName, parentLegion, marinesCount, world));

        SpaceMarineManager.add(spaceMarine, spaceMarines);

    }

    public static void remove(int id, SpaceMarineSet spaceMarines) {
        spaceMarines.delete(id);
    }

    public static void clear(SpaceMarineSet spaceMarines) {
        spaceMarines.clear();
    }

    public static void maxByCreationDate(SpaceMarineSet spaceMarines){
        Optional<SpaceMarine> spaceMarine = spaceMarines.get().stream().max(Comparator.comparing(SpaceMarine::getCreationDate));
        System.out.println(spaceMarine);
    }

    public static void filterByWeaponType(String weaponType, SpaceMarineSet spaceMarines) {
        LinkedHashSet<SpaceMarine> set = spaceMarines.get().stream().filter(o -> o.getWeaponType().toString().equals(weaponType)).collect(Collectors.toCollection(LinkedHashSet::new));
        set.forEach(System.out::println);
    }

}
