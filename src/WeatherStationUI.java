import java.util.EnumMap;

/**
 * Interface to define a method for updating temperature displays
 */
public interface WeatherStationUI {
    void update(EnumMap<MeasurementUnit, Double> readings);
}
