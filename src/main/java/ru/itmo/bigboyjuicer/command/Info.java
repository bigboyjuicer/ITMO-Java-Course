package ru.itmo.bigboyjuicer.command;

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
    public List<String> execute() {
        try {
            String type = "SpaceMarine";
            File file = new File(FileManager.path.toString());
            float usableSpace = (float) file.length() / 1000;
            FileTime creationTime =
                    (FileTime) Files.getAttribute(Path.of(FileManager.path.toString()), "creationTime");
            int size = Server.spaceMarineSet.getSet().size();

            String result = String.format("Тип коллекции: %s\nДата инициализации: %s\nЗанимаемое место: %.1f КБ\nКол-во элементов: %d", type, creationTime
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
                    .format(dateTimeFormat), usableSpace, size);
            return List.of(result);
        } catch (IOException e) {
            System.out.println("Файл не был найден");
        }

        return List.of("Error");
    }
}
