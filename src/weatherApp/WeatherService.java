package weatherApp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherService {
    private final String apiKey = "7f91cc0d6ad9fdd13eef110b11349c24";  // Replace with your API key
    private final String apiUrl = "http://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeather(String city) {
        try {
            String urlString = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=metric";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the response to JSON
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Extract relevant data
            String cityName = jsonResponse.getString("name");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            String description = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

            // Create and return WeatherData object
            return new WeatherData(cityName, temperature, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
}

