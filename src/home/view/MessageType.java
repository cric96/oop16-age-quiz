package home.view;

import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import javafx.scene.control.Alert.AlertType;

/**
 * MessageType represent the possible message that the application can show.
 */
public enum MessageType {
    /**
     * alert message.
     */
    ALERT("ALERT", AlertType.WARNING),

    /**
     * error message.
     */
    ERROR("ERROR", AlertType.ERROR),

    /**
     * exit message.
     */
    EXIT("EXIT", AlertType.WARNING);

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
        return BundleLanguageManager.get().getBundle(Bundles.DIALOG).getString(this.messageTitle);
    }

    /**
     * @return message title.
     */
    public AlertType getAlertType() {
        return this.alertType;
    }
}
