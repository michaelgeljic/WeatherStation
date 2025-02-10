
/**
 * Class for a (simulated) sensor of the temper. We assume the "real"
 * sensor returns a number in the range 0 .. 65565 which is calibrated to
 * be a Kelvin temperature to the nearest 100th of a degree. That is:
 *
 * 0 = 0 degrees Kelvin = -273.15 degrees Celsius
 * 27315 = 273.15 degrees Kelvin = 0 degrees Celsius
 * 29315 = 293.15 degrees Kelvin = 20 degrees Celsius
 *
 * @author Michael J. Lutz
 * @author Kristina Marasovic [kristina.marasovic@rit.edu]
 */
import java.util.Random;   // to simulate random temperature fluctuations.

public class TemperatureSensor {

    /*
     * Min and max readings this sensor will actually report:
     *    23315 = -40 C
     *    32315 =  50 C
     * and the default (initial read)
     *    29315 =  20 C
     */
    private static final int MINREADING = 23315;
    private static final int MAXREADING = 32315;
    private static final int DEFAULT = 29315;

    private int currentReading;         // current sensor read
    private boolean increasing = true;  // TRUE if temperature tending up
    private Random rand = new Random(); // simulate random temp, changes.

    /*
     * Initialize the sensor to the DEFAULT value.
     */
    public TemperatureSensor() {
        currentReading = DEFAULT;
    }

    /*
     * Give a new read based on the last read and whether the
     * temperature is trending up or down. We assume that the temperature
     * has a 80% chance of continuing on its current trend and 20%
     * chance of changing direction. Also, we will not allow changes
     * outside of the specific min. and max. temperatures.
     */
    public int read() {
        final double CUTOFF = 0.8;     // 80% chance to continue temp. trend
        final int MAXCHANGE = 200;     // maximum change in 1/100ths degree
        final int MINCHANGE = 100;     // minimum change in 1/100ths degree
        int tempChange;                // absolute value of the temp. change

        if (rand.nextDouble() > CUTOFF) {
            increasing = !increasing;         // switch direction
        }

        tempChange = rand.nextInt(MAXCHANGE - MINCHANGE) + MAXCHANGE;
        currentReading = currentReading + tempChange * (increasing ? 1 : -1);

        /*
         * Limit readings to the specified (simulated) range.
         */
        if (currentReading >= MAXREADING) {
            currentReading = MAXREADING;
            increasing = false;
        } else if (currentReading <= MINREADING) {
            currentReading = MINREADING;
            increasing = true;
        }

        return currentReading;
    }
}
