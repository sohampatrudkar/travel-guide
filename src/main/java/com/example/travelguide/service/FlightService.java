package com.example.travelguide.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService {

    @Value("${api.flight.api_key}")
    private String apiKey;

    @Value("${api.flight.base_url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getFlightTime(String flightNumber) {
        String url = baseUrl + "/flights?access_key=" + apiKey + "&flight_iata=" + flightNumber;
        return restTemplate.getForObject(url, String.class);
    }
}
