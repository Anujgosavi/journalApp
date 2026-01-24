package net.ImissHer.demo.service;

import jakarta.annotation.PostConstruct;
import net.ImissHer.demo.WeatherResponse.WeatherResp;
import net.ImissHer.demo.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class WeatherService {

        @Value("${weather.api.key}")
        private String key;

        @Autowired
       private RestTemplate restTemplate;

        @Autowired
        private  AppCache appCache ;

        @Autowired
        private RedisService redisService ;



        public WeatherResp getWeather(String city) {

            WeatherResp weatherResponse = redisService.get("weather_of_" + city, WeatherResp.class);
            if (weatherResponse != null) {
                return weatherResponse;
            } else {
                redisService.get(city, WeatherService.class);
                String finalapi = appCache.APP_CACHE.get("weather.key").replace("CITY", city).replace("KEY", key);
                ResponseEntity<WeatherResp> response = restTemplate.exchange(finalapi, HttpMethod.GET, null, WeatherResp.class);
                WeatherResp body = response.getBody();
                if(body!=null){
                    redisService.set("weather_of_" + city, body , 300l);
                }
                return body;
            }
        }
    }





