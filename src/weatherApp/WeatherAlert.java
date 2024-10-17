package weatherApp;

public class WeatherAlert {
    public void checkAlert(WeatherData weatherData) {
        if (weatherData.getTemperature() > 30) {
            System.out.println("Heat Alert! The temperature in " + weatherData.getCity() + " is " + weatherData.getTemperature() + "°C.");
        }
        // Add more conditions as needed
    }
}
