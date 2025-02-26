package edu.rit.croatia.swen383.g3.ui;

import edu.rit.croatia.swen383.g3.observer.*;

import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import javafx.application.Application;
import edu.rit.croatia.swen383.g3.util.*;
/*
 * UIFactory is responsible for creating different UI instances dynamically.
 */
public class UIFactory {
    /**
     * creates a UI instance based on the provided UItype.
     * @param type The type of UI to create.
     * @param station The WeatherStation instance for the UI to observer.
     * @return the corresponding Observer instance.
     * @throws IllegalArgumentException if the UIType is unknown.
     */
    public static Observer createUI(UIType type, WeatherStation station){
        if (type == null) {
            throw new IllegalArgumentException("UI type cannot be null");
        }

        switch (type) {
            case TEXT:
                return new TextUI(station);
            case SWING:
                return new SwingUI(station);
            case JAVAFX:
                JavaFXUI.setWeatherStation(station);
                new Thread(()-> Application.launch(JavaFXUI.class)).start();
                return JavaFXUI.getInstance();
                
            default:
                throw new IllegalArgumentException("Unknown UI type: " + type);
        }
    }
}
