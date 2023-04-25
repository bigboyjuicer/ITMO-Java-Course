package validator;

import type.Command;

public class CommandValidator {

    public static boolean noArgValidator(String command) {
        Command cmd = null;
        try {
            Command.valueOf(command);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public static boolean argValidator(String arg) {
        return arg.split(" ").length == 1;
    }

}
