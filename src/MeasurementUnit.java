import java.util.ArrayList;
import java.util.List;

/**
 * Enum for measurement conversion factors
 * Supports temperature and pressure
 *
 * @author Kristina Marasovic <kristina.marasovic@rit.edu>
 */
public enum MeasurementUnit {
    KELVIN(1.0, 0.0),
    CELSIUS(1.0,-273.15),
    FAHRENHEIT(1.8, -459.67),
    INHG(0.0002953,0.0),
    MBAR(0.01,0.0);

    private final double cf1;
    private final double cf2;


    /**
     * Constructor for MeasurementUnit
     * @param type the type of sensor (TEMPERATURE OR PRESSURE)
     * @param cf1 Multiplication factor
     * @param cf2 Addition factor
     */
    MeasurementUnit(double cf1, double cf2){
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
    public static List<MeasurementUnit> valuesOf(){
        List<MeasurementUnit> result = new ArrayList<>();
        for(MeasurementUnit unit : values()){
            if (unit.type ==) {
                result.add(unit);
            }
        }
        return result;
    }
}
