package edu.rit.croatia.swen383.g3.ws;

import javafx.application.Application;
import java.util.Scanner;

import edu.rit.croatia.swen383.g3.ui.*;


public class WeatherStationRunner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Select UI:");
        System.out.println("1. Swing UI");
        System.out.println("2. JavaFX UI");
        System.out.println("3. Text UI");
        System.out.print("Enter choice: ");

        int choice = in.nextInt();
        in.close();

        WeatherStation station = new WeatherStation();

        switch (choice) {
            case 1:
                new SwingUI(station);
                break;
            case 2:
                JavaFXUI.setWeatherStation(station);
                new Thread(() -> Application.launch(JavaFXUI.class)).start();
                break;
            case 3:
                new TextUI(station);
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        new StatisticsDisplay(station);
        new ForecastDisplay(station);

        new Thread(station).start();
    }
}
