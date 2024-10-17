package weatherApp;

public class WeatherData {
    private String city;
    private double temperature;
    private String description;

    public WeatherData(String city, double temperature, String description) {
        this.city = city;
        this.temperature = temperature;
        this.description = description;
    }

    // Getter for city
    public String getCity() {
        return city;
    }

    // Getter for temperature
    public double getTemperature() {
        return temperature;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "WeatherData{" +
                "city='" + city + '\'' +
                ", temperature=" + temperature +
                ", description='" + description + '\'' +
                '}';
    }
}

