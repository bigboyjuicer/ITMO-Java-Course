package types;

public enum DayTime {
    MORNING("Morning"),
    AFTERNOON("Afternoon"),
    EVENING("Evening"),
    NIGHT("Night");

    private String name;

    DayTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
