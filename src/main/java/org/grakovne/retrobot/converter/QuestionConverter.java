package org.grakovne.retrobot.converter;

import org.grakovne.retrobot.entity.QuestionEntity;
import org.grakovne.retrobot.response.CoreMessageResponse;
import org.springframework.stereotype.Service;

@Service
public class QuestionConverter extends RetroConverter<QuestionEntity, CoreMessageResponse> {
    private final CoreMessageSenderConverter senderConverter;

    public QuestionConverter(CoreMessageSenderConverter senderConverter) {
        super(CoreMessageResponse::new);
        this.senderConverter = senderConverter;
    }

    @Override
    protected CoreMessageResponse from(QuestionEntity question, CoreMessageResponse response) {
        response.setCreatedAt(question.getCreatedAt());
        response.setId(question.getId());
        response.setText(question.getQuestion());
        response.setSender(senderConverter.convert(question.getSender()));

        return response;
    }
}
