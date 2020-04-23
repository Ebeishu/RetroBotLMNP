package org.grakovne.retrobot.service.core;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.repository.RetroSenderRepository;
import org.springframework.stereotype.Service;

/**
 * Core service to process sender of discussion topics.
 */
@Service
public class CoreSenderService {

    private final RetroSenderRepository senderRepository;

    public CoreSenderService(RetroSenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

    /**
     * Creates a new one sender or just supply existing one.
     */
    public CoreMessageSenderEntity swallowSender(RequestedMessageForm form) {
        return senderRepository
                .findByUsername(form.getSenderUsername())
                .orElseGet(() -> saveOne(form));
    }

    /**
     * Knows how to persist a new one sender to storage.
     */
    public CoreMessageSenderEntity saveOne(RequestedMessageForm form) {
        CoreMessageSenderEntity sender = new CoreMessageSenderEntity();

        sender.setFirstName(form.getSenderFirstName());
        sender.setLastName(form.getSenderLastName());
        sender.setUsername(form.getSenderUsername());

        return senderRepository.save(sender);
    }
}
