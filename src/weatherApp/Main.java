package weatherApp;
	
	public class Main {
	    public static void main(String[] args) {
	        WeatherService weatherService = new WeatherService();
	        WeatherAlert weatherAlert = new WeatherAlert();
	        
	        // Specify the city for which you want to fetch the weather
	        String city = "London"; // You can change this to any city you want

	        // Fetch the weather data
	        WeatherData weatherData = weatherService.getWeather(city);

	        // Check if weather data was retrieved successfully
	        if (weatherData != null) {
	            System.out.println("Weather in " + weatherData.getCity() + ":");
	            System.out.println("Temperature: " + weatherData.getTemperature() + "°C");
	            System.out.println("Description: " + weatherData.getDescription());

	            // Check for weather alerts
	            weatherAlert.checkAlert(weatherData);
	        } else {
	            System.out.println("Could not retrieve weather data for " + city);
	        }
	    }
	}

