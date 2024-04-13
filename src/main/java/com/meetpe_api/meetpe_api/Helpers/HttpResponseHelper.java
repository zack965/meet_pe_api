package com.meetpe_api.meetpe_api.Helpers;

import com.meetpe_api.meetpe_api.DTO.Responses.Global.GlobalCreatedResponse;

import java.util.HashMap;

public class HttpResponseHelper {
    public static HashMap<String, String> getGlobalCreatedResponse(String key,String value) {
        HashMap<String, String> message = new HashMap<>();
        message.put(key,value);
        return message;
    }
}
