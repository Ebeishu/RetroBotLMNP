package org.grakovne.retrobot.entity;

import org.grakovne.retrobot.entity.enumeration.OptType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "retro_opt_sender_entity")
public class OptSenderEntity extends RetroEntity {

    @OneToOne
    @JoinColumn(name = "sender_id")
    private CoreMessageSenderEntity sender;

    @Enumerated(EnumType.STRING)
    private OptType type;

    public CoreMessageSenderEntity getSender() {
        return sender;
    }

    public void setSender(CoreMessageSenderEntity sender) {
        this.sender = sender;
    }

    public OptType getType() {
        return type;
    }

    public void setType(OptType type) {
        this.type = type;
    }
}
