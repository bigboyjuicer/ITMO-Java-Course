package entity;

import interfaces.Movable;
import types.Genders;
import types.Places;

import java.util.Objects;

public class Person extends Human {

    private final String name;

    public Person(String name, Genders gender) {
        super(gender);
        this.name = name;
    }

    @Override
    public void move(Places place) {
        setPlace(place);
        System.out.println(name + " moved to " + place.getName());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "gender=" + getGender() +
                "name='" + name + '\'' +
                '}';
    }
}
