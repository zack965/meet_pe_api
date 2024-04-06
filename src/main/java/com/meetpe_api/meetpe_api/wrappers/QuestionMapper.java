package com.meetpe_api.meetpe_api.wrappers;

import com.meetpe_api.meetpe_api.DTO.Responses.Questions.QuestionChoiceDTO;
import com.meetpe_api.meetpe_api.DTO.Responses.Questions.QuestionDTO;
import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {
    public QuestionDTO mapQuestionToDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionText(question.getQuestionText());
        dto.setQuestionKey(question.getQuestionKey());
        dto.setChoices(QuestionChoiceToQuestionChoiceDTO(question.getChoices()));
        return dto;
    }
    public List<QuestionChoiceDTO> QuestionChoiceToQuestionChoiceDTO(List<QuestionChoice> questionChoices) {
        List<QuestionChoiceDTO> questionChoicesDTOs = new ArrayList<>();


        for (int i = 0; i < questionChoices.size(); i++) {
            QuestionChoiceDTO questionChoiceDTO = new QuestionChoiceDTO();
            questionChoiceDTO.setChoiceText(questionChoices.get(i).getChoiceText());
            questionChoicesDTOs.add(questionChoiceDTO);
        }
        return questionChoicesDTOs;
    }
    public List<QuestionDTO> mapQuestionsToDTOs(List<Question> questions) {
        List<QuestionDTO> dtos = new ArrayList<>();
        for (Question question : questions) {
            dtos.add(mapQuestionToDTO(question));
        }
        return dtos;
    }

}
