package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.Server;
import ru.itmo.bigboyjuicer.manager.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Info extends AbstractCommand implements Serializable {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Info() {
        super("info");
    }

    @Override
    public List<String> execute(SpaceMarineSet spaceMarines) {
        try {
            String type = "SpaceMarine";
            File file = new File(FileManager.path.toString());
            float usableSpace = (float) file.length() / 1000;
            FileTime creationTime =
                    (FileTime) Files.getAttribute(Path.of(FileManager.path.toString()), "creationTime");
            int size = spaceMarines.getSet().size();

            String result = String.format("Type of the collection: %s\nCreation date: %s\nUsable space: %.1f КБ\nNumber of elements: %d", type, creationTime
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .format(dateTimeFormat), usableSpace, size);
            return List.of(result);
        } catch (IOException e) {
            System.out.println("File was not found");
        }

        return List.of("Error");
    }
}
