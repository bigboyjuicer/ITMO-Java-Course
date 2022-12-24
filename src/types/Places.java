package types;

public enum Places {
    FLAT("flat"),
    CINEMA("cinema"),
    STADIUM("stadium"),
    CANTEEN("canteen"),
    STREET("street");

    private final String name;

    Places(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Places{" +
                "name='" + name + '\'' +
                '}';
    }
}
