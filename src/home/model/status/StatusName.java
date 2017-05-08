package home.model.status;

import java.util.ResourceBundle;

import home.utility.BundleLanguageManager;

/**
 * Define all name of status.
 */
public enum StatusName {
    /**
     * 
     */
    KNOWLEDGE,
    /**
     * 
     */
    HAPPINESS,
    /**
     * 
     */
    HEALTH,
    /**
     * 
     */
    TECHNICAL;
    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle("StatusBundle").getString(this.name());
    }
}
