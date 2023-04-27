import entity.SpaceMarineSet;
import manager.CommandManager;
import manager.FileManager;
import manager.LoggerManager;

import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main {

  public static void main(String[] args) {
    SpaceMarineSet spaceMarines = new SpaceMarineSet(FileManager.selectAll());

    LoggerManager.createLogger();

    while (CommandManager.active) {
      try {
        CommandManager.menu(new Scanner(System.in).nextLine().toLowerCase(), spaceMarines);
      } catch (NoSuchElementException ex) {
      }
    }
  }
}
