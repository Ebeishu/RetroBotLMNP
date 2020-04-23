package org.grakovne.retrobot.service.core;

import org.grakovne.retrobot.entity.CoreMessageEntity;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.repository.RetroMessageRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.ZonedDateTime;
import java.util.List;

import static org.springframework.data.domain.Sort.by;

/**
 * Core service to process discussion topics.
 */
@Service
public class CoreMessageService {

    private final CoreSenderService senderService;
    private final RetroMessageRepository messageRepository;

    public CoreMessageService(CoreSenderService senderService,
                              RetroMessageRepository messageRepository) {
        this.senderService = senderService;
        this.messageRepository = messageRepository;
    }

    /**
     * Creates a new one message or just supply existing one.
     */
    public CoreMessageEntity swallowMessage(RequestedMessageForm form) {
        return messageRepository
                .findByText(form.getText())
                .orElseGet(() -> saveOne(form));
    }

    /**
     * Knows how to persist a new one message to storage.
     */
    public CoreMessageEntity saveOne(RequestedMessageForm form) {
        CoreMessageEntity message = new CoreMessageEntity();

        message.setCreatedAt(ZonedDateTime.now());
        message.setSender(senderService.swallowSender(form));
        message.setText(form.getText());

        return messageRepository.save(message);
    }

    /**
     * Shows all saved previously messages.
     */
    public List<CoreMessageEntity> getHistory() {
        return messageRepository.findAll();
    }

    public List<CoreMessageEntity> findForDates(ZonedDateTime from, ZonedDateTime to) {
        return messageRepository.findAll(toDatesSpecification(from, to), by(Sort.Direction.DESC, "createdAt"));
    }

    private Specification<CoreMessageEntity> toDatesSpecification(ZonedDateTime from, ZonedDateTime to) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            Predicate fromPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), from);
            Predicate toPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), to);
            return criteriaBuilder.and(fromPredicate, toPredicate);

        };
    }
}
