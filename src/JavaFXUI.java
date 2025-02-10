
/**
 * JavaFXUI is a JavaFX application that displays temperature readings in both
 * Celsius and Kelvin formats. It provides a simple UI with labels to show
 * temperature values and allows updating them dynamically.
 *
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class JavaFXUI extends Application {

    private Label celsiusField;
    private Label kelvinField;

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

        // Set up Kelvin display
        VBox kelvinBox = createTemperatureDisplay(" Kelvin ");
        kelvinField = (Label) kelvinBox.getChildren().get(1);
        gridPane.add(kelvinBox, 0, 0);

        // Set up Celsius display
        VBox celsiusBox = createTemperatureDisplay(" Celsius ");
        celsiusField = (Label) celsiusBox.getChildren().get(1);
        gridPane.add(celsiusBox, 1, 0);

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

    /**
     * Updates the Kelvin temperature label with the given temperature.
     *
     * @param temperature the temperature value to set in Kelvin
     */
    public void setKelvinLabel(double temperature) {
        Platform.runLater(() -> kelvinField.setText(String.format("%6.2f", temperature)));
    }

    /**
     * Updates the Celsius temperature label with the given temperature.
     *
     * @param temperature the temperature value to set in Celsius
     */
    public void setCelsiusLabel(double temperature) {
        Platform.runLater(() -> celsiusField.setText(String.format("%6.2f", temperature)));
    }

    /**
     * The main method launches the JavaFX application. The launch() method
     * blocks the main thread and waits until the application is closed,
     * allowing JavaFX to manage the event loop and UI updates.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
