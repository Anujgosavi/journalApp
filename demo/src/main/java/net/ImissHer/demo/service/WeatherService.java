package net.ImissHer.demo.service;

import net.ImissHer.demo.WeatherResponse.WeatherResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static String key = "7250b84fae215ef026c25d7e7cd5bd5a" ;

    private static String api = "https://api.openweathermap.org/data/2.5/weather?q=City,IN&units=metric&appid="+key ;
   @Autowired
    RestTemplate restTemplate ;

    public WeatherResp getWeather(String city){
       String finalapi =  api.replace("City" , city) ;
        ResponseEntity<WeatherResp>  response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResp.class);

        return response.getBody() ;

    }

}
