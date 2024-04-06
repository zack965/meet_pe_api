package com.meetpe_api.meetpe_api.Services;

import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import com.meetpe_api.meetpe_api.Repositories.QuestionChoiceRepository;
import com.meetpe_api.meetpe_api.Repositories.QuestionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionChoiceService {
    private QuestionChoiceRepository questionChoiceRepository;
    private QuestionRepository questionRepository;

    public QuestionChoiceService(QuestionChoiceRepository questionChoiceRepository, QuestionRepository questionRepository) {
        this.questionChoiceRepository = questionChoiceRepository;
        this.questionRepository = questionRepository;
    }

    @PostConstruct
    public void init() {
        /* default values */
        List<Question> existingQuestions = questionRepository.findAll();
        List<String> keys_questions = DataLoadingService.loadListOfStringData(existingQuestions.size(), "main_app_txt_choice");
        for (int i = 0; i < existingQuestions.size(); i++) {
            String CurrentQuestionChoiceText = keys_questions.get(i);
            Optional<QuestionChoice> questionChoiceOptionel = questionChoiceRepository.findByChoiceText(CurrentQuestionChoiceText);
            if (questionChoiceOptionel.isEmpty()) {
                QuestionChoice questionChoice = new QuestionChoice();
                questionChoice.setChoiceText(CurrentQuestionChoiceText);
                questionChoice.setQuestion(existingQuestions.get(i));
                questionChoiceRepository.save(questionChoice);
            }
        }
    }



}
