package org.grakovne.retrobot.service.random;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;
import org.grakovne.retrobot.entity.OptSenderEntity;
import org.grakovne.retrobot.entity.enumeration.OptType;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.repository.OptSenderRepository;
import org.grakovne.retrobot.service.core.CoreSenderService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class RandomSenderService {

    private final CoreSenderService coreSenderService;
    private final OptSenderRepository repository;

    public RandomSenderService(CoreSenderService coreSenderService,
                               OptSenderRepository repository) {
        this.coreSenderService = coreSenderService;
        this.repository = repository;
    }

    public CoreMessageSenderEntity findRandomOptInSender() {
        List<OptSenderEntity> optInSenders = repository.findByType(OptType.IN);
        Collections.shuffle(optInSenders);

        return optInSenders
                .stream()
                .findFirst()
                .map(OptSenderEntity::getSender)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find opt in sender"));
    }

    public CoreMessageSenderEntity optSender(RequestedMessageForm form, OptType type) {
        CoreMessageSenderEntity sender = coreSenderService.swallowSender(form);

        OptSenderEntity optSender = repository.findBySender(sender).orElseGet(() -> createOptSender(sender));
        optSender.setType(type);

        return repository.save(optSender).getSender();
    }

    private OptSenderEntity createOptSender(CoreMessageSenderEntity origin) {
        OptSenderEntity sender = new OptSenderEntity();
        sender.setSender(origin);
        sender.setType(OptType.IN);

        return repository.save(sender);
    }

}
