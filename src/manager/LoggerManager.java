package manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 * Class that manages the operation of the logger
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class LoggerManager {

  private static final DateTimeFormatter DATE_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
  private static final Path PATH = Paths.get("log");

  /**
   * Method that creates logger.
   */
  public static void createLogger() {
    try {
      Files.createFile(PATH);
    } catch (IOException e) {

    }
  }

  /**
   * Method that output data to logger.
   * @param info
   */
  public static void logInfo(String info) {
    try (PrintWriter printWriter =
        new PrintWriter(new BufferedWriter(new FileWriter(PATH.toString(), true)))) {
      printWriter.println(LocalDateTime.now().format(DATE_TIME_FORMATTER) + ": " + info);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
