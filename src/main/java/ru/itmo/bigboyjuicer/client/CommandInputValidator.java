package ru.itmo.bigboyjuicer.client;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.itmo.bigboyjuicer.builder.EntityBuilder;
import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.command.ExecuteScript;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.manager.FileManager;
import ru.itmo.bigboyjuicer.type.Command;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CommandInputValidator {

    private static final Gson GSON = Converters.registerLocalDateTime(new GsonBuilder()).create();

    public static AbstractCommand validate(String msg) {
        Scanner scanner = new Scanner(msg);

        try {
            Command commandType = Command.valueOf(scanner.next().trim().toUpperCase());


            if(commandType.name().equals("EXIT")) System.exit(0);

            if (commandType.name().equals("HISTORY")) {
                Client.HISTORY.forEach(System.out::println);
                Client.HISTORY.add("history");
                return null;
            }

            AbstractCommand command = commandType.getCommand();

            if (commandType.isContainsArg() == scanner.hasNextLine()) {
                if(commandType.name().equals("ADD") || commandType.name().equals("ADD_IF_MIN") || commandType.name().equals("REMOVE_GREATER")) {
                    SpaceMarine spaceMarine = GSON.fromJson(scanner.nextLine().trim(), SpaceMarine.class);;
                    if(spaceMarine != null) {
                        command.setElement(spaceMarine);
                        return command;
                    }
                }
                if (commandType.isContainsArg()) {
                    command.setArg(scanner.nextLine().trim());
                    if(commandType.name().equals("EXECUTE_SCRIPT")) {
                        command.execute(null);
                        ExecuteScript.files = new ArrayList<>();
                        return null;
                    }
                }
                return command;
            } else if(commandType.name().equals("ADD") || commandType.name().equals("ADD_IF_MIN") || commandType.name().equals("REMOVE_GREATER")) {
                command.setElement(EntityBuilder.spaceMarineBuilder());
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
