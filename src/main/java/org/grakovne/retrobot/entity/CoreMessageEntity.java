package org.grakovne.retrobot.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * Core Retrospective message entity.
 * Should be used to persist discussion topics.
 */
@Entity
@Table(name = "retro_message")
public class CoreMessageEntity extends RetroEntity {

    private ZonedDateTime createdAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private CoreMessageSenderEntity sender;

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CoreMessageSenderEntity getSender() {
        return sender;
    }

    public void setSender(CoreMessageSenderEntity sender) {
        this.sender = sender;
    }
}
