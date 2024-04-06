package com.meetpe_api.meetpe_api.Repositories;

import com.meetpe_api.meetpe_api.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Long> {
    Optional<Question> findByQuestionText(String QuestionText);
}
