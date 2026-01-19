package net.ImissHer.demo.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResp {

    private String cityName;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private String weatherDescription;

    // maps: "name"
    @JsonProperty("name")
    public void setCityName(String name) {
        this.cityName = name;
    }

    // maps: "main"
    @JsonProperty("main")
    public void unpackMain(Map<String, Object> main) {
        this.temperature = ((Number) main.get("temp")).doubleValue();
        this.feelsLike = ((Number) main.get("feels_like")).doubleValue();
        this.humidity = ((Number) main.get("humidity")).intValue();
    }

    // maps: "weather" (array)
    @JsonProperty("weather")
    public void unpackWeather(List<Map<String, Object>> weather) {
        this.weatherDescription = weather.get(0).get("description").toString();
    }
}
