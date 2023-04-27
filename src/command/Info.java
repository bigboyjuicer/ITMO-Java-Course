package command;

import entity.SpaceMarineSet;
import interfaces.Executable;
import interfaces.Validatable;
import manager.CommandManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/***
 * Class that realize behaviour of "info" command.
 * Outputs useful information about collection.
 *
 * @author Maksim Zinin
 * @version ver 1.0
 */
public class Info implements Executable, Validatable {

  private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  private SpaceMarineSet spaceMarines;

  @Override
  public void execute() {
    try {
      String type = "SpaceMarine";
      File file = new File("data/space_marine");
      float usableSpace = (float) file.length() / 1000;
      FileTime creationTime =
          (FileTime) Files.getAttribute(Path.of("data/space_marine"), "creationTime");
      int size = spaceMarines.getSet().size();

      System.out.printf(
          """
                    Тип коллекции: %s
                    Дата инициализации: %s
                    Занимаемое место: %.1f КБ
                    Кол-во элементов: %d
                    """,
          type,
          creationTime
              .toInstant()
              .atZone(ZoneId.systemDefault())
              .toLocalDate()
              .format(dateTimeFormat),
          usableSpace,
          size);

      CommandManager.executed = true;
      CommandManager.HISTORY.add("info");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean validate(String command, SpaceMarineSet spaceMarines) {
    Scanner scanner = new Scanner(command);
    String aCommand = scanner.next();
    if (scanner.hasNext()) {
      return false;
    }
    if (aCommand.equals("info")) {
      this.spaceMarines = spaceMarines;
      execute();
      return true;
    }
    return false;
  }
}
