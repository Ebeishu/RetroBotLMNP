package org.grakovne.retrobot.response;

import java.time.ZonedDateTime;

/**
 * UI-Friendly discussion topic.
 */
public class CoreMessageResponse {

    private Long id;
    private String text;
    private CoreMessageSenderResponse sender;
    private ZonedDateTime createdAt;

    public String getText() {
        return text;
    }

    public CoreMessageResponse setText(String text) {
        this.text = text;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CoreMessageResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public CoreMessageSenderResponse getSender() {
        return sender;
    }

    public CoreMessageResponse setSender(CoreMessageSenderResponse sender) {
        this.sender = sender;
        return this;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public CoreMessageResponse setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
