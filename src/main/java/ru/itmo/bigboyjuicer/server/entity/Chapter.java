package ru.itmo.bigboyjuicer.server.entity;

import java.io.Serializable;
import java.util.Objects;

public class Chapter implements Serializable {
  private String name;
  private String parentLegion;
  private long marinesCount;
  private String world;

  public Chapter(String name, String parentLegion, long marinesCount, String world) {
    this.name = name;
    this.parentLegion = parentLegion;
    this.marinesCount = marinesCount;
    this.world = world;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Chapter chapter = (Chapter) o;
    return marinesCount == chapter.marinesCount
        && Objects.equals(name, chapter.name)
        && Objects.equals(parentLegion, chapter.parentLegion)
        && Objects.equals(world, chapter.world);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, parentLegion, marinesCount, world);
  }

  @Override
  public String toString() {
    return "Chapter{"
        + "name='"
        + name
        + '\''
        + ", parentLegion='"
        + parentLegion
        + '\''
        + ", marinesCount="
        + marinesCount
        + ", world='"
        + world
        + '\''
        + '}';
  }
}
