package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.entity.SpaceMarineSet;
import ru.itmo.bigboyjuicer.server.Server;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterByWeaponType extends AbstractCommand implements Serializable {

  public FilterByWeaponType() {
    super("filter_by_weapon_type");
  }

  @Override
  public List<String> execute(SpaceMarineSet spaceMarines) {
    Set<SpaceMarine> set =
        spaceMarines.getSet().stream()
            .filter(o -> o.getWeaponType().toString().equalsIgnoreCase(getArg()))
            .collect(Collectors.toSet());
    if (set.isEmpty()) {
      return List.of("Couldn't find any records with this type of weapon in the collection");
    } else {
      return set.stream().sorted().map(SpaceMarine::toString).toList();
    }
  }
}
