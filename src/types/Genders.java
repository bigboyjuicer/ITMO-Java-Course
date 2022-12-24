package types;

public enum Genders {
    MAN("man"),
    WOMAN("woman");

    private String name;

    Genders(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
