package edu.rit.croatia.swen383.g3.sensor;

import java.util.Random;
/*
 * Class for a simulated pressure sensor
 * Implements the Sensor inteface to provide pressure reading
 */
public class PressureSensor implements Sensor {
    private static final int MIN = 2700; // minimum reading
    private static final int MAX = 3200; //maximum reading
    private static final int DEFAULT = 2992; //default reading


    private int currentPressure; // current sensor reading
    private boolean increasing = true; // True if pressure is increasing
    private final Random rand = new Random(); // Simulate random pressure changes


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
        final double CUTOFF = 0.75; // 75% chance to continue trend
        final int MAXDELTA = 20; // maximum read change
        int pressureChange;        // Absoulute value of pressure change

        if(rand.nextDouble() > CUTOFF){
            increasing = !increasing; // Switch direction
        }

        pressureChange = rand.nextInt(MAXDELTA);
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

