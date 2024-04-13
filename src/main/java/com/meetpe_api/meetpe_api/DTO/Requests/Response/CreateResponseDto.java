package com.meetpe_api.meetpe_api.DTO.Requests.Response;

import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import com.meetpe_api.meetpe_api.Entities.User;
import lombok.Data;

@Data
public class CreateResponseDto {
    private Long questionChoiceId;
}
