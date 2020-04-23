package org.grakovne.retrobot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Core Retrospective sender entity.
 * Should be used to persist message's sender info.
 */
@Entity
@Table(name = "retro_sender")
public class CoreMessageSenderEntity extends RetroEntity {

    private String firstName;
    private String lastName;
    private String username;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
