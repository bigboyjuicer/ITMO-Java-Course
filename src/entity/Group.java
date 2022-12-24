package entity;

import types.GroupTypes;
import types.Places;

import java.util.Objects;

public class Group {
    private final Person person1;
    private final Person person2;
    private Places place;
    private GroupTypes type;
    private boolean isOver = false;

    public Group(Person person1, Person person2, Places place, GroupTypes type){
        this.person1 = person1;
        this.person2 = person2;
        this.place = place;
        this.type = type;
    }

    public Person getPerson1() {
        return person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public GroupTypes getType() {
        return type;
    }

    public void setType(GroupTypes type) {
        this.type = type;
    }

    public boolean isOver() {
        return this.isOver;
    }

    public void setOver(boolean isOver) {
        if(isOver) System.out.println("The " + this.getType().getName() + " is over");
        this.isOver = isOver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return isOver() == group.isOver() && Objects.equals(getPerson1(), group.getPerson1()) && Objects.equals(getPerson2(), group.getPerson2()) && getPlace() == group.getPlace() && getType() == group.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPerson1(), getPerson2(), getPlace(), getType(), isOver());
    }

    @Override
    public String toString() {
        return "Group{" +
                "person1=" + person1 +
                ", person2=" + person2 +
                ", place=" + place +
                ", type=" + type +
                ", isOver=" + isOver +
                '}';
    }
}
