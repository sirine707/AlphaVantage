package com.sirine.AlphaVantage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlphaVantageFetcher {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${alphavantage.api.key}")
    private String apiKey;

    private static final String ALPHA_VANTAGE_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=";

    public String fetchDataFromAlphaVantage() {
        String url = ALPHA_VANTAGE_URL + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
