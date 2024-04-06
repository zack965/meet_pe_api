package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.DTO.Responses.Questions.QuestionDTO;
import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Services.QuestionService;
import com.meetpe_api.meetpe_api.wrappers.QuestionMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private QuestionMapper questionMapper;
    private QuestionService questionService;

    public QuestionController(QuestionMapper questionMapper, QuestionService questionService) {
        this.questionMapper = questionMapper;
        this.questionService = questionService;
    }

    @GetMapping("")
    public List<QuestionDTO> getQuestions() {
        List<Question> questions = this.questionService.getAllQuestions();
        return questionMapper.mapQuestionsToDTOs(questions);
    }

}
