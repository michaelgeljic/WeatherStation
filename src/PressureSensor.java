import java.util.Random;
/*
 * Class for a simulated pressure sensor
 * Implements the Sensor inteface to provide pressure reading
 */
public class PressureSensor implements Sensor {
    private static final int MIN = 80000; // 800.00 mbar(low pressure)
    private static final int MAX = 110000; //1100.00 mbar(high pressure)
    private static final int DEFAULT = 101325; // 1013.25 mbar(standard pressure)


    private int currentPressure; // current sensor reading
    private boolean increasing = true; // True if pressure is increasing
    private Random rand = new Random(); // Simulate random pressure changes


    /**
     * Initialize the pressure sensor to the default value
     */
    public PressureSensor(){
        currentPressure = DEFAULT;
    }

    /**
     * Simulate reading the pressure sensor
     * @return the current pressure reading in Pascals(scaled by 100)
     */
    @Override
    public int read() {
        final double CUTOFF = 0.8; // 80% chance to continue trend
        final int MAXCHANGE = 500; // Maximum change in 1/100ths pressure unit
        final int MINCHANGE = 100; // Minumum change in 1/100zhs pressure unit
        int pressureChange;        // Absoulute value of pressure change

        if(rand.nextDouble() > CUTOFF){
            increasing = !increasing; // Switch direction
        }

        pressureChange = rand.nextInt(MAXCHANGE-MINCHANGE) + MINCHANGE;
        currentPressure = currentPressure + pressureChange * (increasing ? 1:-1);
        // Limit readings to the specified range
        if(currentPressure >= MAX){
            currentPressure = MAX;
            increasing = false;
        } else if (currentPressure <= MIN) {
            currentPressure = MIN;
            increasing = true;
            
        }
        return currentPressure;
    }

    
    
}
