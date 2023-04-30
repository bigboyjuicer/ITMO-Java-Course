package builder;

import entity.Chapter;
import entity.Coordinates;
import entity.SpaceMarine;
import validator.InputValidator;
import selector.EnumSelector;
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

    String name = InputValidator.stringValidator("название");
    Coordinates coordinates = coordinatesBuilder();
    float health = InputValidator.floatValidator("здоровье", 1);
    int heartCounter = InputValidator.intValidator("кол-во жизней", 1, 3);
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
    System.out.println("Введите координаты");

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
    System.out.println("Введите chapter");

    String chapterName = InputValidator.stringValidator("название");
    String parentLegion = InputValidator.stringValidator("parentLegion");
    long marinesCount = InputValidator.longValidator("кол-во кораблей");
    String world = InputValidator.stringValidator("мир");

    return new Chapter(chapterName, parentLegion, marinesCount, world);
  }
}
