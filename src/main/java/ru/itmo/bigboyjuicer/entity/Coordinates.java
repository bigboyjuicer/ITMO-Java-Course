package ru.itmo.bigboyjuicer.entity;

import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>, Serializable {
  private long x;
  private long y;

  public Coordinates(long x, long y) {
    this.x = x;
    this.y = y;
  }

  public long getX() {
    return x;
  }

  @Override
  public int compareTo(Coordinates o) {
    return Long.compare(x, o.getX());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return x == that.x && y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "Coordinates{" + "x=" + x + ", y=" + y + '}';
  }
}
