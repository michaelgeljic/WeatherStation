
/**
 * Enum for temperature conversion factors. These factors are used to get raw
 * temperature readings to Kelvin or Celsius degrees.
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public enum TemperatureUnit {
    KELVIN(0),
    CELSIUS(-27315);

    private final int conversionFactor;

    TemperatureUnit(int conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double get(int reading) {
        return (reading + conversionFactor) / 100.0;
    }
}
