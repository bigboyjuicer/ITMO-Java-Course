package ru.itmo.bigboyjuicer.client;

import ru.itmo.bigboyjuicer.builder.EntityBuilder;
import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.command.ExecuteScript;
import ru.itmo.bigboyjuicer.type.Command;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandInputValidator {

    public static AbstractCommand validate(String msg) {
        Scanner scanner = new Scanner(msg);

        try {
            Command commandType = Command.valueOf(scanner.next().trim().toUpperCase());
            Client.HISTORY.add(commandType.getCommand().getName());

            if(commandType.name().equals("EXIT")) System.exit(0);
            if (commandType.name().equals("HISTORY")){
                Client.HISTORY.forEach(System.out::println);
                return null;
            }

            if (commandType.isContainsArg() == scanner.hasNextLine()) {
                AbstractCommand command = commandType.getCommand();
                if(commandType.name().equals("ADD") || commandType.name().equals("ADD_IF_MIN") || commandType.name().equals("REMOVE_GREATER")) command.setElement(EntityBuilder.spaceMarineBuilder());
                if (commandType.isContainsArg()) {
                    command.setArg(scanner.nextLine().trim());
                    if(commandType.name().equals("EXECUTE_SCRIPT")) {
                        command.execute(null);
                        ExecuteScript.files = new ArrayList<>();
                        return null;
                    }
                }
                return command;
            }
            System.out.println("Incorrect command format");
        } catch (NoSuchElementException | IllegalArgumentException ex) {
      System.out.println("""
                        Wrong command.
                        help : display available commands.
                        """);
        }
        return null;
    }

}
