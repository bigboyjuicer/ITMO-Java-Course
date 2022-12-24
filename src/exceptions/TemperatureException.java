package exceptions;

public class TemperatureException extends RuntimeException{
    public TemperatureException() {
        super("You can't survive in this temperature");
    }
}
