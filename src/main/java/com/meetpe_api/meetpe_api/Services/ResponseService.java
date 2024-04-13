package com.meetpe_api.meetpe_api.Services;

import com.meetpe_api.meetpe_api.DTO.Requests.Response.CreateResponseDto;
import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import com.meetpe_api.meetpe_api.Entities.Response;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Repositories.QuestionChoiceRepository;
import com.meetpe_api.meetpe_api.Repositories.ResponseRepository;
import com.meetpe_api.meetpe_api.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {
    private ResponseRepository responseRepository;
    private UserRepository userRepository;
    private QuestionChoiceRepository questionChoiceRepository;

    public ResponseService(ResponseRepository responseRepository, UserRepository userRepository, QuestionChoiceRepository questionChoiceRepository) {
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.questionChoiceRepository = questionChoiceRepository;
    }
    public void saveResponse(CreateResponseDto createResponseDto,User user) {
      //  Optional<User> userOptional = this.userRepository.findById(createResponseDto.getUserId());
        Optional<QuestionChoice> optionalQuestionChoice = this.questionChoiceRepository.findById(createResponseDto.getQuestionChoiceId());
        if (optionalQuestionChoice.isPresent()) {
            QuestionChoice questionChoice = optionalQuestionChoice.get(); // Extracting QuestionChoice object from Optional
            Response response = new Response();
            response.setUser(user);
            response.setQuestionChoice(questionChoice);
            this.responseRepository.save(response);
        }
    }
}
