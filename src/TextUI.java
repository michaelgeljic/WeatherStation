
/**
 * The TextUI class is responsible for displaying information
 * from the associated weather station object.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
public class TextUI {

    /**
     * Prints the temperature reading in both Kelvin and Celsius.
     *
     * @param reading The temperature value to be displayed, interpreted using
     * the TemperatureUnit enum.
     */
    private void print(int reading) {
        System.out.printf(
                "Reading is %6.2f degrees K and %6.2f degrees C%n",
                TemperatureUnit.KELVIN.get(reading),
                TemperatureUnit.CELSIUS.get(reading)
        );
    }

}
