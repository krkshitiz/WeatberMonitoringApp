public class WeatherData {
    private String condition;
    private double temperature; // in Celsius
    private long timestamp;

    public WeatherData(String condition, double temperature, long timestamp) {
        this.condition = condition;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public String getCondition() {
        return condition;
    }

    public double getTemperature() {
        return temperature;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
