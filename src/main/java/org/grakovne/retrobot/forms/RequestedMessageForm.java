package org.grakovne.retrobot.forms;

/**
 * Form for received messages from telegram clients.
 */
public class RequestedMessageForm {
    private String senderUsername;
    private String senderFirstName;
    private String senderLastName;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderFirstName() {
        return senderFirstName;
    }

    public void setSenderFirstName(String senderFirstName) {
        this.senderFirstName = senderFirstName;
    }

    public String getSenderLastName() {
        return senderLastName;
    }

    public void setSenderLastName(String senderLastName) {
        this.senderLastName = senderLastName;
    }

    @Override
    public String toString() {
        return "RequestedMessageForm{" +
                "senderUsername='" + senderUsername + '\'' +
                ", senderFirstName='" + senderFirstName + '\'' +
                ", senderLastName='" + senderLastName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
