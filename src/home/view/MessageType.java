package home.view;

/**
 * MessageType represent the possible message that the application can show.
 */
public enum MessageType {
    /**
     * alert message.
     */
    ALERT("Alert"),

    /**
     * error message.
     */
    ERROR("Error"),

    /**
     * exit message.
     */
    EXIT("Exit");

    private String messageTitle;

    /**
     * @param title of message
     */
    MessageType(final String title) {
        this.messageTitle = title;
    }

    /**
     * @return message title.
     */
    public String getMessageTitle() {
        return this.messageTitle;
    }
}
