import java.util.Timer;
import java.util.TimerTask;

public class WeatherMonitor {
    private WeatherService weatherService;
    private Database database;
    private AlertSystem alertSystem;

    public WeatherMonitor() {
        weatherService = new WeatherService();
        database = new Database();
        alertSystem = new AlertSystem(35.0); // Temperature threshold
    }

    public void startMonitoring(String city) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    WeatherData data = weatherService.getWeather(city);
                    String summary = String.format("Date: %s, Temp: %.2f°C, Condition: %s", 
                                                    data.getTimestamp(), data.getTemperature(), data.getCondition());
                    database.saveSummary(summary);
                    System.out.println(summary);

                    if (alertSystem.checkAlert(data.getTemperature())) {
                        System.out.println("ALERT: Temperature exceeds threshold!");
                    }
                } catch (Exception e) {
                    System.out.println("Error fetching weather data: " + e.getMessage());
                }
            }
        }, 0, 300000); // Every 5 minutes
    }

    public static void main(String[] args) {
        WeatherMonitor monitor = new WeatherMonitor();
        monitor.startMonitoring("Delhi");
    }
}
