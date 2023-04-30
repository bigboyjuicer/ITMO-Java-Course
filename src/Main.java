import entity.SpaceMarineSet;
import manager.CommandManager;
import manager.FileManager;
import manager.LoggerManager;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());

    LoggerManager.createLogger();

    while (CommandManager.active) {
      try {
        String command = scanner.nextLine().toLowerCase().trim();
        if(!command.isEmpty()) {
          CommandManager.menu(command, spaceMarines);
        } else {
          System.out.println("""
                              Поле не может быть пустым.
                              Введите help для помощи.
                              """);
        }
      } catch (NoSuchElementException ex) {
        System.out.println("Завершение программы...");
        System.exit(0);
      }
    }
  }
}
