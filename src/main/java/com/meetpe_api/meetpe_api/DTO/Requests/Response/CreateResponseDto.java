package com.meetpe_api.meetpe_api.DTO.Requests.Response;

import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import com.meetpe_api.meetpe_api.Entities.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateResponseDto {
    @NotNull(message = "questionChoiceId is required")
    @Min(value = 1, message = "questionChoiceId must be a number greater than or equal to 1")
    private Long questionChoiceId;
}
