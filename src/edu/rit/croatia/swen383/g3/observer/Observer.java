package edu.rit.croatia.swen383.g3.observer;

/**
 * Observer interface for implementing the Observer pattern
 * Any class that implements this interface will receive updates
 * from a Subject when there are changes
 */

public interface Observer {


    /**
     * Method to be called when the Subject notifies the observers
     */
    void update();
    
}
