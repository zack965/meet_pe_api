package com.meetpe_api.meetpe_api.Services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class DataLoadingService {
    public static List<String> loadListOfStringData(int length , String main_text_txt ) {
        List<String> questionTexts = new ArrayList<>();

        for (int i=0; i < length; i++) {
            questionTexts.add(main_text_txt+"_"+i);
        }
        return questionTexts;
    }
}
