package com.charly.sbSec3Jwt.escuelaRural.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private static final String WEATHER_API_URL = "https://api.open-meteo.com/v1/forecast";
    private static final double LATITUDE = -34.6131;
    private static final double LONGITUDE = -58.3772;
    private static final String TIMEZONE = "America/Sao_Paulo";

    public boolean isLluvioso() {
        RestTemplate restTemplate = new RestTemplate();
        
        String url = UriComponentsBuilder.fromHttpUrl(WEATHER_API_URL)
                .queryParam("latitude", LATITUDE)
                .queryParam("longitude", LONGITUDE)
                .queryParam("current", "rain")
                .queryParam("timezone", TIMEZONE)
                .toUriString();

        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

        if (response != null && response.getCurrent() != null) {
            return response.getCurrent().getRain() > 0;
        }
        return false;
    }

    static class WeatherResponse {
        private Current current;

        public Current getCurrent() {
            return current;
        }

        public void setCurrent(Current current) {
            this.current = current;
        }

        static class Current {
            private double rain;

            public double getRain() {
                return rain;
            }

            public void setRain(double rain) {
                this.rain = rain;
            }
        }
    }
}
