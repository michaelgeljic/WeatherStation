
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingUI extends JFrame {

    private JLabel celsiusField;   // Displays current Celsius reading
    private JLabel kelvinField;    // Displays current Kelvin reading

    /**
     * Font object containing font details for rendering text.
     */
    private static final Font labelFont = new Font(Font.SERIF, Font.PLAIN, 72);

    /**
     * Constructs and initializes the SwingUI frame with panels and labels for
     * displaying temperature readings in Kelvin and Celsius.
     */
    public SwingUI() {
        super("Weather Station");

        // Set layout as a grid with 1 row and multiple columns
        this.setLayout(new GridLayout(1, 0));

        // Initialize and add Kelvin display panel
        JPanel panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Kelvin ", panel);
        kelvinField = createLabel("", panel);

        // Initialize and add Celsius display panel
        panel = new JPanel(new GridLayout(2, 1));
        this.add(panel);
        createLabel(" Celsius ", panel);
        celsiusField = createLabel("", panel);

        // Configure frame settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Updates the label displaying the Kelvin temperature.
     *
     * @param temperature the temperature in Kelvin
     */
    public void setKelvinJLabel(double temperature) {
        kelvinField.setText(String.format("%6.2f", temperature));
    }

    /**
     * Updates the label displaying the Celsius temperature.
     *
     * @param temperature the temperature in Celsius
     */
    public void setCelsiusJLabel(double temperature) {
        celsiusField.setText(String.format("%6.2f", temperature));
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
}
