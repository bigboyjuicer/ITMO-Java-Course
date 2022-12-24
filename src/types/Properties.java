package types;

public enum Properties {
    STICKY("sticky"),
    SMOOTH("smooth");

    private String name;
    Properties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
