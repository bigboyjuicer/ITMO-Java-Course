package entity;

import types.FamilyRoles;
import types.Genders;
import types.Places;

import java.util.Objects;

public class Parent extends Human {

    private final FamilyRoles role;

    public Parent(FamilyRoles role) {
        super(role == FamilyRoles.FATHER ? Genders.MAN : Genders.WOMAN);
        this.role = role;
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
