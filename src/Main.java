import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Select UI:");
        System.out.println("1. Swing UI");
        System.out.println("2. JavaFX UI");
        System.out.println("3. Text UI");
        System.out.print("Enter choice: ");

        int choice = in.nextInt();
        in.close();

        switch (choice) {
            case 1:
                SwingUI swingUI = new SwingUI();
                new Thread(new WeatherStation(swingUI)).start();
                break;
            case 2:
                JavaFXUI.main(args);
                break;
            case 3:
                TextUI textUI = new TextUI();
                textUI.start();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
