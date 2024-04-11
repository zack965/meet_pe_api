package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.DTO.Responses.Questions.QuestionChoiceDTO;
import com.meetpe_api.meetpe_api.DTO.Responses.Questions.QuestionDTO;
import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Services.QuestionService;
import com.meetpe_api.meetpe_api.wrappers.QuestionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("app/questions")
public class QuestionController {
    private QuestionMapper questionMapper;
    private QuestionService questionService;

    public QuestionController(QuestionMapper questionMapper, QuestionService questionService) {
        this.questionMapper = questionMapper;
        this.questionService = questionService;
    }

    @GetMapping("")
    @Operation(security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "API-KEY") })
    public List<QuestionDTO> getQuestions() {
        List<Question> questions = this.questionService.getAllQuestions();
        return questionMapper.mapQuestionsToDTOs(questions);
    }

    @GetMapping("/{question_key}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "API-KEY") })
    public ResponseEntity<Question> getQuestionChoices(@PathVariable("question_key") String questionKey) {
        Optional<Question> question = this.questionService.GetQuestionByKey(questionKey);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
