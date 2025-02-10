/**
 * Interface to define a method for updating temperature displays
 */
public interface WeatherDisplay {
    void updateTemperature(TemperatureUnit unit, double value);
}
