package types;

public enum Sizes {
    LARGE("large"),
    MEDIUM("medium"),
    SMALL("small");

    private String name;

    Sizes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
