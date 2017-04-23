package home.view.fx;

import javafx.scene.control.Alert.AlertType;

/**
 * MessageType represent the possible message that the application can show.
 */
public enum FXMessageType {
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
    private AlertType alert;

    /**
     * @param title of message
     */
    FXMessageType(final String title, final AlertType alert) {
        this.messageTitle = title;
        this.alert = alert;
    }

    /**
     * @return message title.
     */
    public String getMessageTitle() {
        return this.messageTitle;
    }

    /**
     * 
     * @return AllertType.
     */

    public AlertType getAlertType() {
        return this.alert;
    }
}