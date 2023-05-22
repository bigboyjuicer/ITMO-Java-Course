package ru.itmo.bigboyjuicer.command;

import ru.itmo.bigboyjuicer.entity.SpaceMarine;
import ru.itmo.bigboyjuicer.interfaces.Executable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractCommand implements Executable, Serializable {
  @Serial private static final long serialVersionUID = 6529685098267757690L;
  private String name;
  private String arg;
  private SpaceMarine element;

  public AbstractCommand(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArg() {
    return arg;
  }

  public void setArg(String arg) {
    this.arg = arg;
  }

  public SpaceMarine getElement() {
    return element;
  }

  public void setElement(SpaceMarine element) {
    this.element = element;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractCommand that = (AbstractCommand) o;
    return Objects.equals(name, that.name) && Objects.equals(arg, that.arg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, arg);
  }
}
