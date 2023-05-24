package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.client.Client;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExecuteScript extends AbstractCommand {

  public static List<String> files = new ArrayList<>();
  private List<String> commands = new ArrayList<>();
  private boolean isExecuted = false;

  public ExecuteScript() {
    super("execute_script");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    addFile();
    if(!isExecuted) {
      for (String command : commands) {
        Client.menu(command);
      }
    } else {
      isExecuted = false;
    }
    return null;
  }

  public void addFile() {
    if (!files.contains(getArg())) {
      files.add(getArg());
      fillCommandsFromScript();
    } else {
      isExecuted = true;
    }
  }

  private void fillCommandsFromScript() {
    try (Scanner scanner = new Scanner(new FileReader(getArg()))) {
      commands = new ArrayList<>();
      while (scanner.hasNextLine()) {
        commands.add(scanner.nextLine());
      }
      execute(null);
    } catch (FileNotFoundException e) {
      System.out.println("Written file does not exist or is not readable");
    }
  }
}
