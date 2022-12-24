package types;

public enum Actions {
    SIT("sit"),
    OPEN("open"),
    UNDERSTAND("understand"),
    COME("come"),
    LOOK("look"),
    SPECTATE("spectate");

    private final String name;

    Actions(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Actions{" +
                "name='" + name + '\'' +
                '}';
    }
}
