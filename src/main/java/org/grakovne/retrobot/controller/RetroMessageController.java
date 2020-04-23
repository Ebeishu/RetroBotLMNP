package org.grakovne.retrobot.controller;

import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.grakovne.retrobot.service.core.CoreMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Knows how to save topic for nearest Retrospective.
 */
@Service
public class RetroMessageController extends RequestedMessageController {

    private final CoreMessageService coreMessageService;

    public RetroMessageController(CoreMessageService coreMessageService) {
        this.coreMessageService = coreMessageService;
    }

    @Override
    public List<String> getKeys() {
        return List.of("#Retro", "#ретро", "#дляретро");
    }

    @Override
    public String getReplyMessage() {
        return "Спасибо за полезный фидбек!\nМы обязательно разберем это на ретро \uD83D\uDE42";
    }

    @Override
    protected void processMessage(RequestedMessageForm form) {
        coreMessageService.swallowMessage(form);
    }

}
