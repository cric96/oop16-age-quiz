package home.model.status;

import home.utility.BundleLanguageManager;
import home.utility.Bundles;

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
        return BundleLanguageManager.get().getBundle(Bundles.STATUS).getString(this.name());
    }
}
