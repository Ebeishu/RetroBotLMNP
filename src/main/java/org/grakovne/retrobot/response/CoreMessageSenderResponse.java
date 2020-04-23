package org.grakovne.retrobot.response;

/**
 * UI-Friendly discussion topic's sender.
 */
public class CoreMessageSenderResponse {

    private String username;
    private String firstName;
    private String lastName;

    public String getUsername() {
        return username;
    }

    public CoreMessageSenderResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CoreMessageSenderResponse setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CoreMessageSenderResponse setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
