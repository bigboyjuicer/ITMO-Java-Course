package types;

public enum Colors {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private String name;
    Colors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
