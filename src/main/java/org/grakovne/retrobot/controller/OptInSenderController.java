package org.grakovne.retrobot.controller;

import org.grakovne.retrobot.entity.enumeration.OptType;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.service.random.RandomSenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptInSenderController extends RequestedMessageController {

    private final RandomSenderService senderService;

    public OptInSenderController(RandomSenderService senderService) {
        this.senderService = senderService;
    }

    @Override
    public List<String> getKeys() {
        return List.of("#явделе", "#optin", "#выберименя");
    }

    @Override
    public String getReplyMessage() {
        return "Отлично, ты в Игре \uD83D\uDE42";
    }

    @Override
    protected void processMessage(RequestedMessageForm form) {
        senderService.optSender(form, OptType.IN);
    }
}
