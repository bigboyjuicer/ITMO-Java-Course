import entity.SpaceMarineSet;
import manager.CommandManager;
import manager.FileManager;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());

        try {
            while (CommandManager.active) {
                CommandManager.menu(scanner.nextLine().toLowerCase().trim(), spaceMarines);
                scanner.reset();
            }
        } catch (NoSuchElementException ex) {
            System.out.println("""
                    Неверная команда.
                    help : вывести справку по доступным командам.
                    """);
        }
    }
}