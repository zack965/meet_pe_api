package com.meetpe_api.meetpe_api.exceptions.ErrorResponses;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Getter
public class CustomErrorResponse {
    private final String message;
    // Optional getter for timestamp
    private final String timestamp; // Optional, for error logging

    public CustomErrorResponse(String message) {
        this.message = message;
        this.timestamp = String.valueOf(System.currentTimeMillis()); // Optional, for error logging
    }

}
