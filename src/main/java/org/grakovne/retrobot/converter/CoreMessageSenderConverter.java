package org.grakovne.retrobot.converter;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;
import org.grakovne.retrobot.response.CoreMessageSenderResponse;
import org.springframework.stereotype.Service;

/**
 * Converts core retrospective topic's sender into UI-friedly way.
 */
@Service
public class CoreMessageSenderConverter extends RetroConverter<CoreMessageSenderEntity, CoreMessageSenderResponse> {

    protected CoreMessageSenderConverter() {
        super(CoreMessageSenderResponse::new);
    }

    @Override
    protected CoreMessageSenderResponse from(CoreMessageSenderEntity sender, CoreMessageSenderResponse response) {
        response.setFirstName(sender.getFirstName());
        response.setLastName(sender.getLastName());
        response.setUsername(sender.getUsername());

        return response;
    }
}
