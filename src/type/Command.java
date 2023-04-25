package type;

public enum Command {

    HELP("help"),
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    UPDATE("update"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    SAVE("save"),
    EXECUTE_SCRIPT("execute_script"),
    EXIT("exit"),
    ADD_IF_MIN("add_if_min"),
    REMOVE_GREATER("remove_greater"),
    HISTORY("history"),
    MAX_BY_CREATION_DATE("max_by_creation_date"),
    FILTER_BY_WEAPON_TYPE("filter_by_weapon_type"),
    PRINT_ASCENDING("print_ascending");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                '}';
    }
}
