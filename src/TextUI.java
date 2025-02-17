import java.util.EnumMap;

/**
 * The TextUI class is responsible for displaying information
 * from the associated weather station object.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
public class TextUI implements WeatherStationUI {

    /**
     * Prints the sensor reading in a structured format.
     *
     * @param readings EnumMap containing the measurement values
     */

    @Override
    public void update(EnumMap<MeasurementUnit, Double> readings) {
        StringBuilder temperatureLine = new StringBuilder("TEMPERATURE      ");
        for (MeasurementUnit unit : MeasurementUnit.valuesOf(SensorType.TEMPERATURE)){
            temperatureLine.append(String.format("%6.2f %s  ", readings.get(unit), unit.name()));
        }
        System.out.println(temperatureLine);

        StringBuilder pressureLine = new StringBuilder("PRESSURE        ");
        for(MeasurementUnit unit: MeasurementUnit.valuesOf(SensorType.PRESSURE)){
            pressureLine.append(String.format("%6.2f %s  ", readings.get(unit), unit.name()));
        }
        System.out.println(pressureLine);

        System.out.println();
    }
}
