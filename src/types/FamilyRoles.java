package types;

public enum FamilyRoles {
    MOTHER("Mother"),
    FATHER("Father");

    private final String name;
    FamilyRoles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "FamilyRoles{" +
                "name='" + name + '\'' +
                '}';
    }
}
