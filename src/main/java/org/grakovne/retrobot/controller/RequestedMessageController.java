package org.grakovne.retrobot.controller;

import org.apache.commons.lang3.tuple.Pair;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Knows how to process requested message and apply it when need to some business way.
 */
public abstract class RequestedMessageController {

    private final Logger logger;

    public RequestedMessageController() {
        logger = LoggerFactory.getLogger(getClass());
    }

    /**
     * It happens when message from telegram client is received and should be processed.
     */
    public Pair<Class, Boolean> onMessage(RequestedMessageForm form) {
        if (isMessageContainsKey(form.getText())) {
            try {
                processMessage(form);
                logger.info("Message " + form + " has been processed by: " + this.getClass().getSimpleName());
            } catch (Exception ex) {
                logger.warn("Unable to process message: " + form + " due:" + ex);
                return Pair.of(getClass(), false);
            }
            return Pair.of(getClass(), true);
        }

        return Pair.of(getClass(), false);
    }

    /**
     * Knows which text should be contained into message to process by the controller implementation.
     */
    public abstract List<String> getKeys();

    /**
     * Knows what text should be sent in reply message.
     */
    public abstract String getReplyMessage();

    /**
     * Knows how to process received message business way.
     */
    protected abstract void processMessage(RequestedMessageForm text);

    private boolean isMessageContainsKey(String text) {
        if (null == text) {
            return false;
        }

        return getKeys()
                .stream()
                .map(String::toLowerCase)
                .anyMatch(anotherString -> text.toLowerCase().contains(anotherString));
    }
}
