
/**
 * The TextUI class is responsible for displaying information
 * from the associated weather station object.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
public class TextUI implements WeatherStationUI {

    /**
     * Prints the temperature reading for all defined TemperatureUnits.
     *
     * @param reading raw temperature reading
     */
    private void print(int reading) {
        System.out.print("Reading is: ");
        for (TemperatureUnit unit : TemperatureUnit.values()){
            System.out.printf("%6.2f degrees %s ", unit.get(reading), unit.name());
        }
        System.out.println();
    }

    @Override
    public void update(TemperatureUnit unit, double value) {
        print((int) (value)); // uses the existing print() method
    }

    

}
