package selector;

import validator.InputValidator;
import type.AstartesCategory;
import type.Weapon;

/***
 * Class that provides selection of enum types.
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class EnumSelector {

  public static Weapon weaponSelection() {
    System.out.println("Выберите тип оружия (число)");
    System.out.println("1 - HEAVY_BOLTGUN, 2 - BOLT_PISTOL, 3 - BOLT_RIFLE, 4 - INFERNO_PISTOL");

    return switch (InputValidator.intValidator("категорию", 1, 4)) {
      case (1) -> Weapon.HEAVY_BOLTGUN;
      case (2) -> Weapon.BOLT_PISTOL;
      case (3) -> Weapon.BOLT_RIFLE;
      case (4) -> Weapon.INFERNO_PISTOL;
      default -> null;
    };
  }

  public static AstartesCategory categorySelection() {
    System.out.println("Выберите категорию (число)");
    System.out.println("1 - AGGRESSOR, 2 - LIBRARIAN, 3 - CHAPLAIN");

    return switch (InputValidator.intValidator("категорию", 1, 3)) {
      case (1) -> AstartesCategory.AGGRESSOR;
      case (2) -> AstartesCategory.LIBRARIAN;
      case (3) -> AstartesCategory.CHAPLAIN;
      default -> null;
    };
  }
}
