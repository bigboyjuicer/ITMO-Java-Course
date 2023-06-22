package ru.itmo.bigboyjuicer.server.manager;

import ru.itmo.bigboyjuicer.entity.Chapter;
import ru.itmo.bigboyjuicer.entity.Coordinates;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.type.AstartesCategory;
import ru.itmo.bigboyjuicer.type.Weapon;

import java.sql.*;
import java.util.LinkedHashSet;

public class SpaceMarineManager {

    public static LinkedHashSet<SpaceMarine> selectAll() throws SQLException {
        try (Connection connection = DatabaseManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet spaceMarineSet = statement.executeQuery("SELECT * FROM space_marine");
            LinkedHashSet<SpaceMarine> set = new LinkedHashSet<>();
            while (spaceMarineSet.next()) {
                set.add(
                        new SpaceMarine(
                                spaceMarineSet.getLong("id"),
                                spaceMarineSet.getString("space_marine_name"),
                                new Coordinates(spaceMarineSet.getLong("x"), spaceMarineSet.getLong("y")),
                                spaceMarineSet.getTimestamp("creation_date").toLocalDateTime(),
                                spaceMarineSet.getFloat("health"),
                                spaceMarineSet.getInt("heart_count"),
                                AstartesCategory.valueOf(spaceMarineSet.getString("category")),
                                Weapon.valueOf(spaceMarineSet.getString("weapon_type")),
                                new Chapter(spaceMarineSet.getString("chapter_name"), spaceMarineSet.getString("parent_legion"), spaceMarineSet.getInt("marines_count"), spaceMarineSet.getString("world"))
                        )
                );
            }
            return set;
        }
    }

    public static boolean insertSpaceMarine(SpaceMarine spaceMarine) {
        try (Connection connection = DatabaseManager.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO space_marine VALUES(nextval('space_marine_id'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            fillPreparedStatement(preparedStatement, spaceMarine);

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                spaceMarine.setId(resultSet.getLong(1));
                return true;
            }
        } catch (SQLException ignored) {
        }
        return false;
    }

    public static boolean updateSpaceMarine(SpaceMarine spaceMarine) {
        try (Connection connection = DatabaseManager.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE space_marine SET space_marine_name = ?, x = ?, y = ?, creation_date = ?, health = ?, heart_count = ?, category = ?, weapon_type = ?, chapter_name = ?, parent_legion = ?, marines_count = ?, world = ? WHERE id = ?");

            fillPreparedStatement(preparedStatement, spaceMarine);
            preparedStatement.setLong(13, spaceMarine.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean deleteSpaceMarine(int id) {
        try(Connection connection = DatabaseManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM space_marine WHERE id = ?");
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private static void fillPreparedStatement(PreparedStatement preparedStatement, SpaceMarine spaceMarine) throws SQLException {
        preparedStatement.setString(1, spaceMarine.getName());
        preparedStatement.setLong(2, spaceMarine.getCoordinates().getX());
        preparedStatement.setLong(3, spaceMarine.getCoordinates().getY());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(spaceMarine.getCreationDate()));
        preparedStatement.setFloat(5, spaceMarine.getHealth());
        preparedStatement.setInt(6, spaceMarine.getHeartCount());
        preparedStatement.setString(7, spaceMarine.getCategory().name());
        preparedStatement.setString(8, spaceMarine.getWeaponType().name());
        preparedStatement.setString(9, spaceMarine.getChapter().getName());
        preparedStatement.setString(10, spaceMarine.getChapter().getParentLegion());
        preparedStatement.setInt(11, spaceMarine.getChapter().getMarinesCount());
        preparedStatement.setString(12, spaceMarine.getChapter().getWorld());
    }

}
