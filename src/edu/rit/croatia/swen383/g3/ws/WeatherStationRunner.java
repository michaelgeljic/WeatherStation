package edu.rit.croatia.swen383.g3.ws;
import javafx.application.Application;
import java.util.Scanner;

import WeatherStationUI;
import edu.rit.croatia.swen383.g3.ui.JavaFXUI;
import edu.rit.croatia.swen383.g3.ui.SwingUI;
import edu.rit.croatia.swen383.g3.ui.TextUI;


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

        WeatherStationUI ui = null;

        switch (choice) {
            case 1:
                ui = new SwingUI();
                break;
            case 2:
                new Thread(() -> Application.launch(JavaFXUI.class)).start();
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ui = JavaFXUI.getInstance();
                break;
            case 3:
                ui = new TextUI();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        if (ui!=null) {
            new Thread(new WeatherStation(ui)).start();
            
        }
    }
}
