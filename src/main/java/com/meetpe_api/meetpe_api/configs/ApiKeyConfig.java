package com.meetpe_api.meetpe_api.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiKeyConfig {


    @Value("${api.key}")
    private String apiKey;


    public String getApiKey() {
        return apiKey;
    }
}
