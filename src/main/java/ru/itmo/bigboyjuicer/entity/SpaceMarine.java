package ru.itmo.bigboyjuicer.entity;

import ru.itmo.bigboyjuicer.type.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {

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
        this.id = -1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.health = health;
        this.heartCount = heartCount;
        this.category = category;
        this.weaponType = weaponType;
        this.chapter = chapter;
    }

    public SpaceMarine(
            long id,
            String name,
            Coordinates coordinates,
            LocalDateTime creationDate,
            float health,
            int heartCount,
            AstartesCategory category,
            Weapon weaponType,
            Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
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

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public int getHeartCount() {
        return heartCount;
    }

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    public AstartesCategory getCategory() {
        return category;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
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
