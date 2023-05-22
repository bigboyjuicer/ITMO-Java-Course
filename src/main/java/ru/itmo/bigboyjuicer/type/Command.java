package ru.itmo.bigboyjuicer.type;

import ru.itmo.bigboyjuicer.command.*;

public enum Command {
    SHOW(new Show(), false),
    HELP(new Help(), false),
    INFO(new Info(), false),
    ADD(new Add(), false),
    UPDATE(new Update(), true),
    REMOVE_BY_ID(new RemoveById(), true),
    CLEAR(new Clear(), false),
    EXIT(new Exit(), false),
    ADD_IF_MIN(new AddIfMin(), false),
    REMOVE_GREATER(new RemoveGreater(), false),
    MAX_BY_CREATION_DATE(new MaxByCreationDate(), false),
    FILTER_BY_WEAPON_TYPE(new FilterByWeaponType(), true);

    private AbstractCommand command;
    private boolean containsArg;

    Command(AbstractCommand command, boolean containsArg) {
        this.command = command;
        this.containsArg = containsArg;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    public boolean isContainsArg() {
        return containsArg;
    }

    public void setContainsArg(boolean containsArg) {
        this.containsArg = containsArg;
    }
}
