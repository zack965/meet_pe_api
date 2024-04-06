package com.meetpe_api.meetpe_api.Services;

import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Repositories.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    public void init() {
        /* default rows */

        List<String> questionTexts = DataLoadingService.loadListOfStringData(10,"main_app_txt");


        List<String> questionKeys = DataLoadingService.loadListOfStringData(10,"main_app_key");


        for (int i = 0; i < questionTexts.size(); i++) {
            String questionText = questionTexts.get(i);
            String questionKey = questionKeys.get(i);
            Optional<Question> existingQuestion = questionRepository.findByQuestionText(questionText);
            if (existingQuestion.isEmpty()) {
                Question question = new Question();
                question.setQuestionKey(questionKey);
                question.setQuestionText(questionText);
                questionRepository.save(question);
            }

        }

    }
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

}
