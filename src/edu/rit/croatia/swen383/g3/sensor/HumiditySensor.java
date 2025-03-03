package edu.rit.croatia.swen383.g3.sensor;

import edu.rit.marasovic.swen383.thirdparty.sensor.HumidityReader;

/**
 * The HumiditySensor class acts as an adapter for the third-party
 * HumidityReader,
 * converting its output to a percentage-based integer value.
 */
public class HumiditySensor implements Sensor {

    private final HumidityReader humidityReader;

    /**
     * Constructor that initializes the HumidityReader instance.
     */
    public HumiditySensor() {
        this.humidityReader = new HumidityReader();
    }

    /**
     * Reads the humidity value from the sensor and returns it as a percentage.
     * 
     * @return an integer representing the humidity percentage (0-100).
     */
    @Override
    public int read() {
        return (humidityReader.get() * 100);
    }
}
