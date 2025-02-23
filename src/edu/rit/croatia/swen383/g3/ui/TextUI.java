package edu.rit.croatia.swen383.g3.ui;


import edu.rit.croatia.swen383.g3.util.MeasurementUnit;
import edu.rit.croatia.swen383.g3.util.SensorType;
import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import edu.rit.croatia.swen383.g3.observer.*;

/**
 * The TextUI class is responsible for displaying information
 * from the associated weather station object.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
public class TextUI implements Observer {
    private final WeatherStation station;

    /*Constructs a TextUi and registers it as an observer */

    public TextUI(WeatherStation station){
        this.station = station;
        station.attach(this);
    }

    /**
     * Updates the TextUi display with latest sensor readings.
     *
     * 
     */

    @Override
    public void update() {
        StringBuilder temperatureLine = new StringBuilder("TEMPERATURE      ");
        for (MeasurementUnit unit : MeasurementUnit.valuesOf(SensorType.TEMPERATURE)){
            temperatureLine.append(String.format("%6.2f %s  ", station.getReading(unit), unit.name()));
        }
        System.out.println(temperatureLine);

        StringBuilder pressureLine = new StringBuilder("PRESSURE        ");
        for(MeasurementUnit unit: MeasurementUnit.valuesOf(SensorType.PRESSURE)){
            pressureLine.append(String.format("%6.2f %s  ",station.getReading(unit), unit.name()));
        }
        System.out.println(pressureLine);

        System.out.println();
    }
}
