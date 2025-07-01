package edu.rit.croatia.swen383.g3.ws;

/*
 * Class for a simple computer based weather station that reports the current
 * temperature (in Celsius) every second. The station is attached to a
 * sensor that reports the temperature as a 16-bit number (0 to 65535)
 * representing the Kelvin temperature to the nearest 1/100th of a degree.
 *
 * This class is implements Runnable so that it can be embedded in a Thread
 * which runs the periodic sensing.
 */

import java.util.EnumMap;

import edu.rit.croatia.swen383.g3.observer.Subject;
import edu.rit.croatia.swen383.g3.sensor.*;
import edu.rit.croatia.swen383.g3.util.*;


public class WeatherStation extends Subject implements Runnable {

    private static final long PERIOD = 1000; // 1 sec = 1000 ms
    private final EnumMap<SensorType, Sensor> sensorMap; // Maps SensorType to Sensor
    private final EnumMap<MeasurementUnit, Double> readingMap; // Maps MeasurementUnit to values

   
    /*
     * Initializes the WeatherStation with multiple sensors and ui
     * 
     */
    public WeatherStation(EnumMap<SensorType, Sensor> sensorMap) {
        this.sensorMap = sensorMap;
        this.readingMap = new EnumMap<>(MeasurementUnit.class);

    }

    /*
     * runs the weather station continuously reading sensors and updating the UI
     */
    @Override
    public void run() {

        while (true) {

            getSensorReadings(); // Read all sensors
            notifyObservers(); //notify all registered observers aka ui

            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


            // for (TemperatureUnit unit : TemperatureUnit.values()) {
            //     ui.updateTemperature(unit, unit.get(reading));

            // }
            //Update UI with all readings
            
            
            
/* 
            if (ui instanceof TextUI) {
                ui.update(null, reading);

            } else {
                for (TemperatureUnit unit : TemperatureUnit.values()) {
                    ui.update(unit, unit.get(reading));

                }

            }
*/
        }
    }
    /*
    Gets the reading from all sensors from all sensors and stores the measures in readingMap
    */
    private void getSensorReadings(){
        for(SensorType type : SensorType.values()){
            // get the corresponding sensor from sensorMap
           Sensor sensor = sensorMap.get(type);

            if(sensor != null){
                int rawReading = sensor.read(); // read sensor data

                // Convert and store readings in readingMap
                for(MeasurementUnit unit : MeasurementUnit.valuesOf(type)){
                    readingMap.put(unit, unit.get(rawReading));
                }
            } 
        }
    }


    /**
     * retrieves the latest reading for a specific MeasurementUnit
     * 
     * @param unit The measurement unit to retrieve (e.g CELSIUS,KELVIN,MBAR).
     * @return The latest measurement value as a double
     */
    public double getReading(MeasurementUnit unit) {
        return readingMap.getOrDefault(unit,0.0);
    }
    
}
