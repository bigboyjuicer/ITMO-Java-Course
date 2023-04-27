package interfaces;

import entity.SpaceMarineSet;
/***
 * Interface for validation. Implementations can be any type of validation.
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public interface Validatable {
  boolean validate(String command, SpaceMarineSet spaceMarines);
}
