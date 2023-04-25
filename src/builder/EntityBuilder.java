package builder;

import entity.Chapter;
import entity.Coordinates;
import entity.SpaceMarine;
import validator.InputValidator;
import selector.EnumSelector;
import type.AstartesCategory;
import type.Weapon;

public class EntityBuilder {
    public static SpaceMarine spaceMarineBuilder() {

        String name = InputValidator.stringValidator("название");
        Coordinates coordinates = coordinatesBuilder();
        float health = InputValidator.floatValidator("здоровье", 1);
        int heartCounter = InputValidator.intValidator("кол-во жизней", 1, 3);
        AstartesCategory category = EnumSelector.categorySelection();
        Weapon weapon = EnumSelector.weaponSelection();
        Chapter chapter = chapterBuilder();

        return new SpaceMarine(name, coordinates, health, heartCounter, category, weapon, chapter);
    }

    public static Coordinates coordinatesBuilder() {
        System.out.println("Введите координаты");

        int x = InputValidator.intValidator("x", -585);
        int y = InputValidator.intValidator("y");

        return new Coordinates(x, y);
    }

    public static Chapter chapterBuilder() {
        System.out.println("Введите chapter");

        String chapterName = InputValidator.stringValidator("название");
        String parentLegion = InputValidator.stringValidator("parentLegion");
        long marinesCount = InputValidator.longValidator("кол-во кораблей");
        String world = InputValidator.stringValidator("мир");

        return new Chapter(chapterName, parentLegion, marinesCount, world);
    }
}
