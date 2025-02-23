package edu.rit.croatia.swen383.g3.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject ckass that maintains a list of observers and notifies them of updates
 * This class is extended by WeatherStation to manage UI components as observers
 */


public class Subject {
    private final List<Observer> observers = new ArrayList<>();

    /**
     * Attches an observer to the Subject
     * @param observer The observer to add
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * Detches an observer to the Subject
     * @param observer The observer to remove
     */
    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers of an update.
     */
    public void notifyObservers(){
        System.out.println("*********WEATHER UPDATES**********");
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
