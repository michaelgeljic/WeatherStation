
# Weather Station

**Weather Station** is a Java application that simulates real-time weather data using virtual sensors. It supports multiple user interfaces and uses the Observer, Adapter, and Factory design patterns to ensure modularity and extensibility.

---

## Features

- Simulates temperature, humidity, and pressure sensors.
- Supports multiple UIs: Text-based, Swing, and JavaFX.
- Sensors push live updates to display components.
- Sensor and UI creation is abstracted through factories.
- Consistent sensor interface enabled via adapters.

---

## Design Patterns Used

- **Observer Pattern** – Sensors notify display components of data changes.
- **Adapter Pattern** – Standardizes access to different sensor implementations.
- **Factory Pattern** – Instantiates sensor and UI types through centralized factories.

---

## Components

### Model

- `Sensor`: Interface for weather sensors.
- `TemperatureSensor`, `HumiditySensor`, `PressureSensor`: Simulated sensors generating dynamic data.
- `SensorFactory`: Creates sensor instances.
- `Subject` and `Observer`: Interfaces enabling sensor-to-display communication.

### View

- `TextUI`, `SwingUI`, `JavaFXUI`: UI modes for console, Swing, and JavaFX.
- `StatisticsDisplay`, `ForecastDisplay`: Display modules that compute running averages and trends.

### Controller

- `WeatherStationRunner`: Main entry point that starts the application.
- `UIFactory`: Instantiates the chosen UI type.

---

## How It Works

1. The application launches via `WeatherStationRunner`.
2. Sensors are created using `SensorFactory`.
3. The selected UI is launched via `UIFactory`.
4. Sensor readings change over time and notify registered displays.
5. UI components update automatically with new data.

---

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/your-org/WeatherStation.git
   cd WeatherStation
Run Main.java


