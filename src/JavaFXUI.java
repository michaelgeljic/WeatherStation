
/**
 * JavaFXUI is a JavaFX application that displays temperature readings in both
 * Celsius and Kelvin formats. It provides a simple UI with labels to show
 * temperature values and allows updating them dynamically.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
import java.util.EnumMap;
import java.util.Map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class JavaFXUI extends Application implements WeatherStationUI {

    private static JavaFXUI instance;
    private final Map<MeasurementUnit, Label> labelMap = new EnumMap<>(MeasurementUnit.class);

    public JavaFXUI() {
        instance = this;
    }

    public static JavaFXUI getInstance() {
        return instance;
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
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            VBox temperatureBox = createTemperatureDisplay(unit.name());
            Label valuLabel = (Label) temperatureBox.getChildren().get(1);
            labelMap.put(unit, valuLabel);
            gridPane.add(temperatureBox, column++, 0);

        }

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

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
     * updates the temperatrure label dynamically based on the temperatureunit
     * 
     * @param unit        the temperatureunit to update
     * @param temperature the temperature value to set
     */
    public void setLabel(TemperatureUnit unit, double temperature) {
        if (labelMap.containsKey(unit)) {
            Platform.runLater(() -> labelMap.get(unit).setText(String.format("%6.2f", temperature)));
        } else {
            System.out.println("ERROR: temperatureUnit not found in map.");
        }
    }

    /**
     * The main method launches the JavaFX application. The launch() method
     * blocks the main thread and waits until the application is closed,
     * allowing JavaFX to manage the event loop and UI updates.
     *
     * @param args command-line arguments
     */

    @Override
    public void update(EnumMap<MeasurementUnit, Double> labelMap) {
        for (MeasurementUnit unit : MeasurementUnit.values())
            setLabel(unit, labelMap.get(unit));
    }
}
