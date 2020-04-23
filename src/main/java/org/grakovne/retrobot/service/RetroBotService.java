package org.grakovne.retrobot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.commons.lang3.tuple.Pair;
import org.grakovne.retrobot.configuration.RetroBotConfiguration;
import org.grakovne.retrobot.controller.RequestedMessageController;
import org.grakovne.retrobot.forms.RequestedMessageForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Telegram API integration point.
 */
@Service
public class RetroBotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetroBotService.class);

    private final TelegramBot telegramBot;
    private final MessageService messageService;

    private final Map<String, RequestedMessageController> controllers;

    public RetroBotService(List<RequestedMessageController> controllers,
                           RetroBotConfiguration configuration,
                           MessageService messageService) {

        telegramBot = new TelegramBot(configuration.getToken());
        this.messageService = messageService;

        this.controllers = controllers
                .stream()
                .map(controller -> controller.getKeys()
                        .stream()
                        .map(key -> Pair.of(key, controller))
                        .collect(toList()))
                .flatMap(List::stream)
                .collect(toMap(Pair::getLeft, Pair::getRight));
    }

    /**
     * Subscribes to message with business rules.
     */
    public void subscribeToMessages() {
        telegramBot.setUpdatesListener(updates -> {
            try {
                processUpdate(updates);
                LOGGER.info("Batch of updated has been processed");
            } catch (Exception ex) {
                LOGGER.warn("Unable to process updates batch: " + updates + " due:" + ex);
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private Long findChatId(Update update) {
        return ofNullable(update)
                .map(Update::message)
                .map(Message::chat)
                .map(Chat::id)
                .orElse(null);
    }

    private void processUpdate(List<Update> updates) {
        updates
                .stream()
                .filter(Objects::nonNull)
                .forEach(this::processUpdate);
    }

    private void processUpdate(Update update) {
        messageService
                .getKeys(toForm(update))
                .stream()
                .map(controllers::get)
                .filter(Objects::nonNull)
                .collect(toMap(e -> e.getClass().getSimpleName(), p -> p, (p, q) -> p))
                .values()
                .forEach(e -> processWithController(update, e));
    }

    private void processWithController(Update update, RequestedMessageController controller) {
        if (controller.onMessage(toForm(update)).getValue()) {
            replyToMessage(update, controller.getReplyMessage());
        }
    }

    private void replyToMessage(Update update, String message) {
        Long chatId = findChatId(update);

        if (null == chatId) {
            LOGGER.warn("Unable to reply on message: " + update);
            return;
        }

        telegramBot.execute(new SendMessage(chatId, message));
    }

    private RequestedMessageForm toForm(Update update) {
        if (null == update) {
            return null;
        }

        Message message = update.message();

        RequestedMessageForm form = new RequestedMessageForm();

        form.setText(message.text());
        form.setSenderFirstName(message.from().firstName());
        form.setSenderLastName(message.from().lastName());
        form.setSenderUsername(message.from().username());

        return form;
    }


}
