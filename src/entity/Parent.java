package entity;

import exceptions.GenderException;
import interfaces.Movable;
import types.FamilyRoles;
import types.Genders;
import types.Places;

import java.util.Objects;

public class Parent extends Human{

    private FamilyRoles role;

    public Parent(Genders gender, FamilyRoles role) throws GenderException {
        super(gender);
        this.role = role;

        if(role.getName().equals("Mother") && gender.getName().equals("man")) {
            throw new GenderException();
        } else if (role.getName().equals("Father") && gender.getName().equals("woman")) {
            throw new GenderException();
        }
    }

    @Override
    public void move(Places place) {
        setPlace(place);
        System.out.println(role.getName() + " moved to " + place.getName());
    }

    public FamilyRoles getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent parent)) return false;
        if (!super.equals(o)) return false;
        return getRole() == parent.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }

    @Override
    public String toString() {
        return "Parents{" +
                "gender=" + getGender() +
                "role=" + role +
                '}';
    }
}
