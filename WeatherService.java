import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = "9455eb02a6beb1c822ba877de170cc3b"; // API key

    public WeatherData getWeather(String city) throws Exception {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String jsonResponse = response.toString();
        
        // Basic parsing without a library
        String condition = jsonResponse.split("\"main\":\"")[1].split("\"")[0];
        double tempKelvin = Double.parseDouble(jsonResponse.split("\"temp\":")[1].split(",")[0]);
        double tempCelsius = tempKelvin - 273.15; // Convert to Celsius
        long timestamp = Long.parseLong(jsonResponse.split("\"dt\":")[1].split(",")[0]);

        return new WeatherData(condition, tempCelsius, timestamp);
    }
}
