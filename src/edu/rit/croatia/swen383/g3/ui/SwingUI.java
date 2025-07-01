package edu.rit.croatia.swen383.g3.ui;

/**
 * Swing UI class used for displaying information from the
 * associated weather station object.
 * This class extends {@link JFrame}, the outermost container in a Swing
 * application.
 *
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.util.EnumMap;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.rit.croatia.swen383.g3.util.MeasurementUnit;
import edu.rit.croatia.swen383.g3.ws.WeatherStation;
import edu.rit.croatia.swen383.g3.observer.*;
/*SwingUI is Observer that displays real time weather data in graphical interface */
public class SwingUI extends JFrame implements Observer {
    private final WeatherStation station;

    private final EnumMap<MeasurementUnit, JLabel> labelMap = new EnumMap<>(MeasurementUnit.class);
   

    /**
     * Font object containing font details for rendering text.
     */
    private static final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /**
     * Constructs the swingUi and registers it as an observer
     * @param station The WeatherStation instance
     */
    public SwingUI(WeatherStation station) {
        super("Weather Station");
        this.station = station;
        station.attach(this);

        

        // Set layout as a grid with 1 row and columns for each temperature unit
        this.setLayout(new GridLayout(1, MeasurementUnit.values().length));

        // Create and add panels dynamically for each temperature unit
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            this.add(createPanel(unit));
        }

        // Configure frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Updates the label displaying the temperature for a specific unit.
     * 
     * @param unit  The TemperatureUnit to update
     * @param value The temperature reading
     */
    private void setLabel(MeasurementUnit unit, double value) {
        if (labelMap.containsKey(unit)) {
            labelMap.get(unit).setText(String.format("%6.2f", value));

        }
    }

    /**
     * Creates a JPanel containing a temperature display with a title label and a
     * value label, then adds it to the frame
     * 
     * @param unit The TemperatureUnit to create a panel for
     * @return The JPanel containing the title and value labels
     */
    private JPanel createPanel(MeasurementUnit unit) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel titleLabel = createLabel(unit.name());
        JLabel valueLabel = createLabel("");
        // Store the references to the valueLabel in the map
        labelMap.put(unit, valueLabel);
        panel.add(titleLabel);
        panel.add(valueLabel);
        return panel;
    }

    /**
     * Creates a JLabel with the specified title, adds it to the given panel,
     * and returns the label reference for further modification if needed.
     *
     * @param title the text to display in the label
     * @param panel the panel to which the label is added
     * @return the created JLabel instance
     */
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(labelFont);
        return label;
    }
/*Updates the Swing UI with the latest sensor reading */
    @Override
    public void update() {
        for (MeasurementUnit unit : MeasurementUnit.values())
            setLabel(unit, station.getReading(unit));
    }
}
