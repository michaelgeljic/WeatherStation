
/**
 * Swing UI class used for displaying information from the
 * associated weather station object.
 * This class extends {@link JFrame}, the outermost container in a Swing
 * application.
 *
 * @author Michael J. Lutz
 * @author Kristina Marasovic [kristina.marasovic@croatia.rit.edu]
 *
 */
import java.awt.Font;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingUI extends JFrame implements WeatherStationUI{

    private final Map<TemperatureUnit, JLabel> jLabelMap; // Stores labels for temperature units

    /**
     * Font object containing font details for rendering text.
     */
    private static final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /**
     * Constructs and initializes the SwingUI frame with panels and labels for
     * displaying temperature readings for all TemperatureUnit values.
     */
    public SwingUI() {
        super("Weather Station");

        jLabelMap = new EnumMap<>(TemperatureUnit.class);

        // Set layout as a grid with 1 row and columns for each temperature unit
        this.setLayout(new GridLayout(1, TemperatureUnit.values().length));

        // Create and add panels dynamically for each temperature unit
        for(TemperatureUnit unit : TemperatureUnit.values()){
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
     * @param unit The TemperatureUnit to update
     * @param value The temperature reading
     */
    private void setJLabel(TemperatureUnit unit, double value){
        if (jLabelMap.containsKey(unit)) {
            jLabelMap.get(unit).setText(String.format("%6.2f", value));

            
        }else{
            System.out.println("Error: TemperatureUnit not found in map.");
        }
    }



    /**
     * Creates a JPanel containing a temperature display with a title label and a value label, then adds it to the frame
     * 
     * @param unit The TemperatureUnit to create a panel for
     * @return The JPanel containing the title and value labels
     */
    private JPanel createPanel(TemperatureUnit unit){
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel titleLabel = createLabel(unit.name(), panel);
        JLabel valueLabel = createLabel("", panel);
        // Store the references to the valueLabel in the map
        jLabelMap.put(unit, valueLabel);
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
    private JLabel createLabel(String title, JPanel panel) {
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(labelFont);
        panel.add(label);
        return label;
    }

    @Override
    public void update(TemperatureUnit unit, double value) {
        setJLabel(unit, value);
    }
}
