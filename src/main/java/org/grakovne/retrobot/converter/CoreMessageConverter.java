package org.grakovne.retrobot.converter;

import org.grakovne.retrobot.entity.CoreMessageEntity;
import org.grakovne.retrobot.response.CoreMessageResponse;
import org.springframework.stereotype.Service;

/**
 * Converts core retrospective topic into UI-friendly way.
 */
@Service
public class CoreMessageConverter extends RetroConverter<CoreMessageEntity, CoreMessageResponse> {

    private final CoreMessageSenderConverter senderConverter;

    protected CoreMessageConverter(CoreMessageSenderConverter senderConverter) {
        super(CoreMessageResponse::new);
        this.senderConverter = senderConverter;
    }


    @Override
    protected CoreMessageResponse from(CoreMessageEntity message, CoreMessageResponse response) {
        response.setCreatedAt(message.getCreatedAt());
        response.setId(message.getId());
        response.setText(message.getText());
        response.setSender(senderConverter.convert(message.getSender()));

        return response;
    }
}
