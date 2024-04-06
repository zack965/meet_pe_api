package com.meetpe_api.meetpe_api.DTO.Responses.Questions;


import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private String questionText;
    private String questionKey;
    private List<QuestionChoiceDTO> choices;
}
