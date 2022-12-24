package types;

public enum Stuff {
    STAMP("stamp"),
    STONE("stone"),
    CRAYON("crayon"),
    SOLDIER("soldier"),
    PAPER("paper");

    private String name;

    Stuff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
