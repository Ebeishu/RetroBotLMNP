package org.grakovne.retrobot.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "business_question")
public class QuestionEntity extends RetroEntity {

    private ZonedDateTime createdAt;
    private String question;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private CoreMessageSenderEntity sender;

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public CoreMessageSenderEntity getSender() {
        return sender;
    }

    public void setSender(CoreMessageSenderEntity sender) {
        this.sender = sender;
    }
}
