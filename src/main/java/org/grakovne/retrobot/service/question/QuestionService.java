package org.grakovne.retrobot.service.question;

import org.grakovne.retrobot.entity.QuestionEntity;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.repository.QuestionRepository;
import org.grakovne.retrobot.service.core.CoreSenderService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final CoreSenderService senderService;

    public QuestionService(QuestionRepository questionRepository,
                           CoreSenderService senderService) {
        this.questionRepository = questionRepository;
        this.senderService = senderService;
    }

    /**
     * Creates a new one message or just supply existing one.
     */
    public QuestionEntity swallowMessage(RequestedMessageForm form) {
        return questionRepository
                .findByQuestion(form.getText())
                .orElseGet(() -> saveOne(form));
    }

    /**
     * Knows how to persist a new one message to storage.
     */
    public QuestionEntity saveOne(RequestedMessageForm form) {
        QuestionEntity message = new QuestionEntity();

        message.setCreatedAt(ZonedDateTime.now());
        message.setSender(senderService.swallowSender(form));
        message.setQuestion(form.getText());

        return questionRepository.save(message);
    }

    /**
     * Shows all saved previously messages.
     */
    public List<QuestionEntity> getHistory() {
        return questionRepository.findAll();
    }

    public List<QuestionEntity> findForDates(ZonedDateTime from, ZonedDateTime to) {
        return questionRepository.findAll(toDatesSpecification(from, to), by(Sort.Direction.DESC, "createdAt"));
    }

    private Specification<QuestionEntity> toDatesSpecification(ZonedDateTime from, ZonedDateTime to) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            Predicate fromPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from);
            Predicate toPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to);
            return criteriaBuilder.and(fromPredicate, toPredicate);

        };
    }


}
