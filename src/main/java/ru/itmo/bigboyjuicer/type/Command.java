package ru.itmo.bigboyjuicer.type;

import ru.itmo.bigboyjuicer.command.AbstractCommand;
import ru.itmo.bigboyjuicer.command.Show;

public enum Command {
    SHOW(new Show(), false);

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
