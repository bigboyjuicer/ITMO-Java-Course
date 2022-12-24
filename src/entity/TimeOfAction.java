package entity;

import types.DayTime;

public class TimeOfAction {

    private static double temperature;
    private DayTime dayTime;

    public TimeOfAction(DayTime dayTime) {
        this.dayTime = dayTime;
    }

    public static class TemperatureManager {
        public void calculateWeatherType() {
            if(temperature >= 10.0) {
                System.out.println("Warm weather");
            } else {
                System.out.println("Cold weather");
            }
        }
    }

    public static double getTemperature() {
        return temperature;
    }

    public static void setTemperature(double temperature) {
        TimeOfAction.temperature = temperature;
    }

    public DayTime getDayTime() {
        return dayTime;
    }

    public void setDayTime(DayTime dayTime) {
        this.dayTime = dayTime;
    }
}
