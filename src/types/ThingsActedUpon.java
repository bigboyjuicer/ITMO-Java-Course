package types;

public enum ThingsActedUpon {
    NONE("nonel"),
    DOOR("door"),
    WHAT_WAS_SAID("what was said"),
    WINDOW("window"),
    TWILIGHT("twilight"),
    STREET("street"),
    THEM("them");

    private final String name;

    ThingsActedUpon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ThingsActedUpon{" +
                "name='" + name + '\'' +
                '}';
    }
}
