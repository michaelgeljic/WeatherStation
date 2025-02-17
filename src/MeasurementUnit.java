import java.util.ArrayList;
import java.util.List;

/**
 * Enum for measurement conversion factors
 * Supports temperature and pressure
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public enum MeasurementUnit {
    KELVIN(SensorType.TEMPERATURE,1.0, 0.0),
    CELSIUS(SensorType.TEMPERATURE,1.0,-27315),
    FAHRENHEIT(SensorType.TEMPERATURE,1.8, -45967),
    INHG(SensorType.PRESSURE,1,0.0),
    MBAR(SensorType.PRESSURE,33.864,0.0);

    private final SensorType type;
    private final double cf1;
    private final double cf2;


    /**
     * Constructor for MeasurementUnit
     * @param type the type of sensor (TEMPERATURE OR PRESSURE)
     * @param cf1 Multiplication factor
     * @param cf2 Addition factor
     */
    MeasurementUnit(SensorType type, double cf1, double cf2){
        this.type = type;
        this.cf1 = cf1;
        this.cf2 = cf2;
    }

  

    /**
     * Converts a raw sensor reading using the provided conversion factors
     * @param reading Raw reading from the sensor (Kelvin for temp, Pascals for pressure)
     * @return Converted measurement value
     */
    public double get(int reading) {
        return (reading * cf1 + cf2) / 100.0;
    }


    /**
     * Returns a list of MeasurementUnits that belong to a given SensorType
     * @param sensorType the sensor type to filter by
     * @return List of MeasurementUnits belonging to the specified sensor type
     */
    public static List<MeasurementUnit> valuesOf(SensorType sensorType){
        List<MeasurementUnit> result = new ArrayList<>();
        for(MeasurementUnit unit : values()){
            if (unit.type == sensorType) {
                result.add(unit);
            }
        }
        return result;
    }
}
