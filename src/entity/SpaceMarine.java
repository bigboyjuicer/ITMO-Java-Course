package entity;

import type.AstartesCategory;
import type.Weapon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class SpaceMarine implements Comparable<SpaceMarine> {

  private static final DateTimeFormatter dateTimeFormat =
      DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");

  private long id;
  private String name;
  private Coordinates coordinates;
  private LocalDateTime creationDate;
  private float health;
  private int heartCount;
  private AstartesCategory category;
  private Weapon weaponType;
  private Chapter chapter;

  public SpaceMarine(
      String name,
      Coordinates coordinates,
      float health,
      int heartCount,
      AstartesCategory category,
      Weapon weaponType,
      Chapter chapter) {
    this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    this.name = name;
    this.coordinates = coordinates;
    this.creationDate = LocalDateTime.now();
    this.health = health;
    this.heartCount = heartCount;
    this.category = category;
    this.weaponType = weaponType;
    this.chapter = chapter;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public Weapon getWeaponType() {
    return weaponType;
  }

  @Override
  public int compareTo(SpaceMarine o) {
    return name.compareTo(o.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SpaceMarine that = (SpaceMarine) o;
    return id == that.id
        && Float.compare(that.health, health) == 0
        && heartCount == that.heartCount
        && Objects.equals(name, that.name)
        && Objects.equals(coordinates, that.coordinates)
        && Objects.equals(creationDate, that.creationDate)
        && category == that.category
        && weaponType == that.weaponType
        && Objects.equals(chapter, that.chapter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id, name, coordinates, creationDate, health, heartCount, category, weaponType, chapter);
  }

  @Override
  public String toString() {
    return "SpaceMarine{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", coordinates="
        + coordinates
        + ", creationDate="
        + creationDate.format(dateTimeFormat)
        + ", health="
        + health
        + ", heartCount="
        + heartCount
        + ", category="
        + category
        + ", weaponType="
        + weaponType
        + ", chapter="
        + chapter
        + '}';
  }
}
