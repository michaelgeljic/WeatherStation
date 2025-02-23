package edu.rit.croatia.swen383.g3.ui;
import edu.rit.croatia.swen383.g3.observer.Observer;
import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import edu.rit.croatia.swen383.g3.util.MeasurementUnit;

/**
 * ForecastDisplay is an Observer that provides a simple weather forecast.
 * It determines whether today is "Warmer" or "Cooler" based on a temperature threshold.
 */
public class ForecastDisplay implements Observer{
    private final WeatherStation station;
    private String forecast;

/**
 * Constructs a ForecastDisplay and registers it as an observer.
 * @param station The WeatherStation inerface
 * @param station
 */
    public ForecastDisplay(WeatherStation station){
        this.station = station;
        this.forecast = "Stable";
        station.attach(this);
    }
/**
 * Update sthe forecast based on the latest temperature reading.
 * If the temperature is above 28C, it forecasts "Today is warmer".
 * Otherwise, it forecasts "Today is Cooler."
 */
    @Override // from observer
    public void update() {
        double currentTemperature = station.getreading(MeasurementUnit.CELSIUS);

        forecast = (currentTemperature >= 20 ) ? "Today is Warmer" : "Today is Cooler";

        System.out.printf("FORECAST: %s (Temp: %6.2f C)%n", forecast, currentTemperature);
    }
}