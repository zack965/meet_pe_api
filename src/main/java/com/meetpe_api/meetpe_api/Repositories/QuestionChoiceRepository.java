package com.meetpe_api.meetpe_api.Repositories;

import com.meetpe_api.meetpe_api.Entities.Question;
import com.meetpe_api.meetpe_api.Entities.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice,Long> {
    Optional<QuestionChoice> findByChoiceText(String ChoiceText);

}
