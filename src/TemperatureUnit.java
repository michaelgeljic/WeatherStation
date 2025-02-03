public enum TemperatureUnit {
    KELVIN(0),
    CELSIUS(-27315);

    private final int conversionFactor;

    TemperatureUnit(int conversionFactor){
        this.conversionFactor = conversionFactor;
    }

    public double get(int reading){
        return (reading + conversionFactor) / 100.0;

    }
}
