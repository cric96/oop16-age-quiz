package home.view;

import javafx.scene.control.Alert.AlertType;

/**
 * MessageType represent the possible message that the application can show.
 */
public enum MessageType {
    /**
     * alert message.
     */
    ALERT("Alert", AlertType.WARNING),

    /**
     * error message.
     */
    ERROR("Error", AlertType.ERROR),

    /**
     * exit message.
     */
    EXIT("Exit", AlertType.CONFIRMATION);

    private String messageTitle;
    private AlertType alertType;
    /**
     * @param title of message
     */
    MessageType(final String title, final AlertType alert) {
        this.messageTitle = title;
        this.alertType = alert;
    }

    /**
     * @return message title.
     */
    public String getMessageTitle() {
        return this.messageTitle;
    }

    /**
     * @return message title.
     */
    public AlertType getAlertType() {
        return this.alertType;
    }
}