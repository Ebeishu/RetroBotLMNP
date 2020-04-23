package org.grakovne.retrobot.controller;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.service.core.CoreSenderService;
import org.grakovne.retrobot.service.random.RandomSenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomSenderController extends RequestedMessageController {

    private final CoreSenderService coreSenderService;

    private final String randomUserResponseHeader = "Счастливчик сегодняшнего дня: ";

    private final RandomSenderService senderService;

    public RandomSenderController(CoreSenderService coreSenderService, RandomSenderService senderService) {
        this.coreSenderService = coreSenderService;
        this.senderService = senderService;
    }


    @Override
    public List<String> getKeys() {
        return List.of("#randomuser", "#случайный", "#случайник");
    }

    @Override
    public String getReplyMessage() {
        CoreMessageSenderEntity randomUser = senderService.findRandomOptInSender();
        return randomUserResponseHeader + buildUserName(randomUser) + " \uD83D\uDE42";
    }

    @Override
    protected void processMessage(RequestedMessageForm form) {
        coreSenderService.swallowSender(form);
    }

    private String buildUserName(CoreMessageSenderEntity user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
