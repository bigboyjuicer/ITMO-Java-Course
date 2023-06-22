package ru.itmo.bigboyjuicer.server.manager;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileReader("src/main/resources/database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private DatabaseManager() {}
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", PROPERTIES);
    }
    public static void settingUpDatabase() throws SQLException, ClassNotFoundException {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS space_marine (" +
                              " id bigint primary key," +
                              " space_marine_name text not null," +
                              " x bigint check(x > -454)," +
                              " y bigint not null," +
                              " creation_date timestamp not null," +
                              " health numeric not null check(health > 0)," +
                              " heart_count int not null check(heart_count > 0 and heart_count <= 3)," +
                              " category text not null," +
                              " weapon_type text not null," +
                              " chapter_name text not null," +
                              " parent_legion text," +
                              " marines_count int check(marines_count > 0 and marines_count < 1000)," +
                              " world text not null" +
                              ")");
            statement.execute("CREATE SEQUENCE IF NOT EXISTS space_marine_id OWNED BY space_marine.id");
        }
    }
}
