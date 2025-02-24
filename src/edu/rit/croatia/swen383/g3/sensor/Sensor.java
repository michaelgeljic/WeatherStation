/*
 * Sensor interface that defines a method for reading sensor data
 */
public interface Sensor {
    /**
     * Reads the sensor value
     * @return the raw sensor reading as an integer
     */
    int read();
}
