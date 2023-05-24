package ru.itmo.bigboyjuicer.builder;

import ru.itmo.bigboyjuicer.selector.EnumSelector;
import ru.itmo.bigboyjuicer.entity.Chapter;
import ru.itmo.bigboyjuicer.entity.Coordinates;
import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.validator.InputValidator;
import type.AstartesCategory;
import type.Weapon;

/***
 * Class that provides entity building.
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class EntityBuilder {

  /**
   * Method that builds SpaceMarine entity
   * It offers user to input each field of the entity and further return new enityt
   * @return SpaceMarine
   */
  public static SpaceMarine spaceMarineBuilder() {

    String name = InputValidator.stringValidator("name");
    Coordinates coordinates = coordinatesBuilder();
    float health = InputValidator.floatValidator("health", 1);
    int heartCounter = InputValidator.intValidator("heartCount", 1, 3);
    AstartesCategory category = EnumSelector.categorySelection();
    Weapon weapon = EnumSelector.weaponSelection();
    Chapter chapter = chapterBuilder();

    return new SpaceMarine(name, coordinates, health, heartCounter, category, weapon, chapter);
  }
  /**
   * Method that builds Coordinates entity
   * It offers user to input each field of the entity and further return new enityt
   * @return Coordinates
   */
  public static Coordinates coordinatesBuilder() {
    System.out.println("Write coordinates");

    int x = InputValidator.intValidator("x", -585);
    int y = InputValidator.intValidator("y");

    return new Coordinates(x, y);
  }

  /**
   * Method that builds Chapter entity
   * It offers user to input each field of the entity and further return new enityt
   * @return Chapter
   */
  public static Chapter chapterBuilder() {
    System.out.println("Write chapter");

    String chapterName = InputValidator.stringValidator("name");
    String parentLegion = InputValidator.stringValidator("parentLegion");
    long marinesCount = InputValidator.longValidator("marinesCount");
    String world = InputValidator.stringValidator("world");

    return new Chapter(chapterName, parentLegion, marinesCount, world);
  }
}
