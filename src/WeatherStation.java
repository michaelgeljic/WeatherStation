
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
public class WeatherStation implements Runnable {

    private static final long PERIOD = 1000;       // 1 sec = 1000 ms
    private int reading;                    // sensor reading

    private final TemperatureSensor sensor;  // TemperatureUnit sensor.

    private final WeatherDisplay ui;

    /*
     * When a WeatherStation object is created, it in turn creates the sensor
     * object it will use.
     */
    public WeatherStation(WeatherDisplay ui) {
        sensor = new TemperatureSensor();
        this.ui = ui;
    }

    /*
     * The "run" method called by the enclosing Thread object when started.
     * Repeatedly sleeps a second, acquires the current temperature from its
     * sensor, and reports this as a formatted output string.
     */
    @Override
    public void run() {

        while (true) {

            reading = sensor.read();

            for (TemperatureUnit unit : TemperatureUnit.values()){
                ui.updateTemperature(unit, unit.get(reading));
            }

            try {
                Thread.sleep(PERIOD);
            } catch (InterruptedException e) {
                // ignore exceptions
            }
        }
    }
}
