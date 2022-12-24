package entity;

import interfaces.Movable;
import types.Genders;
import types.Places;

import java.util.Objects;

public abstract class Human implements Movable {

    private final Genders gender;
    private Places place;

    public Human(Genders gender) {
        this.gender = gender;
    }

    public abstract void move(Places place);

    public Genders getGender() {
        return gender;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return getGender() == human.getGender() && getPlace() == human.getPlace();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGender(), getPlace());
    }

    @Override
    public String toString() {
        return "Human{" +
                "gender=" + gender +
                ", place=" + place +
                '}';
    }
}
