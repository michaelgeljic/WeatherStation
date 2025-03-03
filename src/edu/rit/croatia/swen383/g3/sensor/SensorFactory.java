package edu.rit.croatia.swen383.g3.sensor;
import edu.rit.croatia.swen383.g3.util.*;

/*
 * SensorFactort us responsible for creating sensor instances dynamically
 */
public class SensorFactory {
    /**
     * Creates a Sensor based on the provided SensorType.
     * @param type the type of sensor to create
     * @return the corresponding sensor instance
     * @throws IllegalArgumentException if the sensortype is unknown
     */
    public static Sensor createSensor(SensorType type) {
        switch (type) {
            case TEMPERATURE:
                return new TemperatureSensor();
            case PRESSURE:
                return new PressureSensor();
            case HUMIDITY:
                return new HumiditySensor();
            default:
                throw new IllegalArgumentException("Unknown sensor type:" + ' ' + type);
        }
    }
    
}
