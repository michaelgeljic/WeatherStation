package edu.rit.croatia.swen383.g3.ui;

import edu.rit.croatia.swen383.g3.observer.Observer;
import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import edu.rit.croatia.swen383.g3.util.MeasurementUnit;

/**
 * The statisticsDisplay class is an observer that displays statistical weather data
 * It prints the latest temperature and pressure readings from the WeatherStation
 */

public class StatisticsDisplay implements Observer{

    private final WeatherStation station;

    /**
     * Constructs a StatisticsDisplay and registers it as an observer to the WeatherStation
     * 
     * @param station The WeatherStation instance providing sensor readings.
     */
    
     public StatisticsDisplay(WeatherStation station){
        this.station = station;
        station.attach(this);
     }

     /**
      * Updates the StatisticsDisplay with the latest temperature and pressure readings.
      * The data is printed in the console in Celsius and milibars.
      */

      @Override
      public void update(){
        double temperature = station.getReading(MeasurementUnit.CELSIUS);
        double pressure = station.getReading(MeasurementUnit.MBAR);

        System.out.printf("Statistics: Avg Temp: %6.2f C   |   Pressure: %6.2f mbar%n", temperature, pressure);
      }
}