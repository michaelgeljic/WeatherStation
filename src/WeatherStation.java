
/*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 * @author Michael J. Lutz
 * @author Kristina Marasovic [kristina.marasovic@rit.edu]
 */

import java.util.EnumMap;

public class WeatherStation implements Runnable {

    private static final long PERIOD = 1000; // 1 sec = 1000 ms
    private final EnumMap<SensorType, Sensor> sensorMap; // Maps SensorType to Sensor

    private final EnumMap<MeasurementUnit, Double> readingMap; // Maps MeasurementUnit to values

    private final WeatherStationUI ui; // UI component

    /*
     * Initializes the WeatherStation with multiple sensors and ui
     * 
     */
    public WeatherStation(WeatherStationUI ui) {
        this.ui = ui;
        this.sensorMap = new EnumMap<>(SensorType.class);
        this.readingMap = new EnumMap<>(MeasurementUnit.class);

        sensorMap.put(SensorType.TEMPERATURE, new TemperatureSensor());
        sensorMap.put(SensorType.PRESSURE, new PressureSensor());
    }

    /*
     * runs the weather station continuously reading sensors and updating the UI
     */
    @Override
    public void run() {

        while (true) {

            getSensorReadings(); // Read all sensors

            // for (TemperatureUnit unit : TemperatureUnit.values()) {
            //     ui.updateTemperature(unit, unit.get(reading));

            // }
            //Update UI with all readings
            
            ui.update(readingMap); 
            
/* 
            if (ui instanceof TextUI) {
                ui.update(null, reading);

            } else {
                for (TemperatureUnit unit : TemperatureUnit.values()) {
                    ui.update(unit, unit.get(reading));

                }

            }
*/
            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                // ignore exceptions
            }
        }
    }
    /*
    Gets the reading from all sensors from all sensors and stores the measures in readingMap
    */
    private void getSensorReadings(){
        Sensor sensor = null;
        for(SensorType type : SensorType.values()){
            // get the corresponding sensor from sensorMap
            sensor = sensorMap.get(type);

            if(sensor != null){
                int rawReading = sensor.read(); // read sensor data

                // Convert and store readings in readingMap
                for(MeasurementUnit unit : MeasurementUnit.valuesOf(type)){
                    readingMap.put(unit, unit.get(rawReading));
                }
            } 
        }
    }
}
