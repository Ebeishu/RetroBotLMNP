package org.grakovne.retrobot.repository;

import org.grakovne.retrobot.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends RetroRepository<QuestionEntity> {

    Optional<QuestionEntity> findByQuestion(String text);
}
