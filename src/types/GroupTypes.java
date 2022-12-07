package types;

public enum GroupTypes {
    CONVERSATION("conversation"),
    FIGHT("fight");

    private final String name;

    GroupTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GroupTypes{" +
                "name='" + name + '\'' +
                '}';
    }
}
