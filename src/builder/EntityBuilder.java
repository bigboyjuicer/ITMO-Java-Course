package builder;

import entity.Chapter;
import entity.Coordinates;
import entity.SpaceMarine;
import handler.InputHandler;
import selector.EnumSelector;
import type.AstartesCategory;
import type.Weapon;

public class EntityBuilder {
    public static SpaceMarine spaceMarineBuilder() {

        String name = InputHandler.stringHandler("название");
        Coordinates coordinates = coordinatesBuilder();
        float health = InputHandler.floatHandler("здоровье", 1);
        int heartCounter = InputHandler.intHandler("кол-во жизней", 1, 3);
        AstartesCategory category = EnumSelector.categorySelection();
        Weapon weapon = EnumSelector.weaponSelection();
        Chapter chapter = chapterBuilder();

        return new SpaceMarine(name, coordinates, health, heartCounter, category, weapon, chapter);
    }

    public static Coordinates coordinatesBuilder() {
        System.out.println("Введите координаты");

        int x = InputHandler.intHandler("x", -585);
        int y = InputHandler.intHandler("y");

        return new Coordinates(x, y);
    }

    public static Chapter chapterBuilder() {
        System.out.println("Введите chapter");

        String chapterName = InputHandler.stringHandler("название");
        String parentLegion = InputHandler.stringHandler("parentLegion");
        long marinesCount = InputHandler.longHandler("кол-во кораблей");
        String world = InputHandler.stringHandler("мир");

        return new Chapter(chapterName, parentLegion, marinesCount, world);
    }
}
