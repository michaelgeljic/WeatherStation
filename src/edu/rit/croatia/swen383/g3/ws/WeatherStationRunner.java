package edu.rit.croatia.swen383.g3.ws;

import javafx.application.Application;

import java.util.EnumMap;
import java.util.Scanner;
import edu.rit.croatia.swen383.g3.observer.*;
import edu.rit.croatia.swen383.g3.sensor.Sensor;
import edu.rit.croatia.swen383.g3.ui.*;
import edu.rit.croatia.swen383.g3.util.SensorType;

/**
 * WeatherStationRunner initializes the WeatherStation and UI components
 * It now fully utilizes the Factory Pattern and does not contain a switch-case.
 */

public class WeatherStationRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Select UI (TEXT, SWING, JAVAFX): ");
        String input = in.next().toUpperCase();
        in.close();

        //Parese user input into UIType
        UIType selectedUIType = UIType.valueOf(input);

        // Create sensors using SensoryFactory
        EnumMap<SensorType, Sensor> sensorMap = new EnumMap<>(SensorType.class);
        sensorMap.put(SensorType.TEMPERATURE, SensorFactory.createSensor(SensorType.TEMPERATURE));
        sensorMap.put(SensorType.PRESSURE, SensorFactory.createSensor(SensorType.PRESSURE));

        //initialize WeatherStation with the sensor map
        WeatherStation station = new WeatherStation(sensorMap);

        //use UIFactory to create UI dynamically
        Observer ui = UIFactory.createUI(selectedUIType, station);

        new StatisticsDisplay(station);
        new ForecastDisplay(station);

        //start WeatherStation thread
        new Thread(station).start();

    }
}
