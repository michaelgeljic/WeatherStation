package edu.rit.croatia.swen383.g3.ui;

/**
 * JavaFXUI is a JavaFX application that displays temperature readings in both
 * Celsius and Kelvin formats. It provides a simple UI with labels to show
 * temperature values and allows updating them dynamically.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
import java.util.EnumMap;
import java.util.Map;
import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import edu.rit.croatia.swen383.g3.observer.*;
import edu.rit.croatia.swen383.g3.util.MeasurementUnit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
/*The JavaFX Ui class provides a graphical user interface for displying weather data
 * it observes the Weather station and updates dynamically when new sensor readings arrive
 */
public class JavaFXUI extends Application implements Observer {

    private static JavaFXUI instance;
    private static WeatherStation station;
    private final EnumMap<MeasurementUnit, Label> labelMap = new EnumMap<>(MeasurementUnit.class);
/*
 * default constructor required by JavaFX
 * stores the iinstance reference
 */
    public JavaFXUI() {
        instance = this;
    }
/*
 * return the single instance of JavaFX Ui
 */
    public static JavaFXUI getInstance() {
        return instance;
    }
/*
 * Sets the weatherStation instance before JavaFx starts
 * this method must be called before launching JavaFx application
 */
    public static void setWeatherStation(WeatherStation ws){
        station = ws;
    }

    /**
     * Starts the JavaFX application and initializes the UI.
     *
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Station");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(20);

        // create and add ui elements
        int column = 0;
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            VBox temperatureBox = createTemperatureDisplay(unit.name());
            Label valuLabel = (Label) temperatureBox.getChildren().get(1);
            labelMap.put(unit, valuLabel);
            gridPane.add(temperatureBox, column++, 0);

        }

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        if (station != null) {
            station.attach(this);
        }

    }

    /**
     * Creates a VBox containing a temperature display with a title label and a
     * value label.
     *
     * @param title the title of the temperature display
     * @return a VBox containing the title and value labels
     */
    private VBox createTemperatureDisplay(String title) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");
        titleLabel.setAlignment(Pos.CENTER);

        Label valueLabel = new Label("");
        valueLabel.setStyle("-fx-font-size: 36px;");
        valueLabel.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, titleLabel, valueLabel);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }

    //
    /**
     * updates a UI label with the latest measurement value
     * uses Platform.runLater() to ensure Ui updates happen on JavaFX thread
     * 
     * @param unit        the measurement unit being updated
     * @param temperature the new value for the unit
     */
    public void setLabel(MeasurementUnit unit, double value) {
        Label label = labelMap.get(unit);
        if (label != null) {
            Platform.runLater(() -> label.setText(String.format("%6.2f", value)));
        }
    }

    /**
     *Updates the JavaFX Ui with the latest sensor readings
     This method is called when WeatherStation notifies observers of new data
     */

    @Override
    public void update() {
        if (station != null) {
            Platform.runLater(() -> {
                for(MeasurementUnit unit : MeasurementUnit.values()){
                    setLabel(unit, station.getReading(unit));
                }
            });
        }
    }
}
