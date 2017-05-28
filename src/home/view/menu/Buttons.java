package home.view.menu;

import home.utility.BundleLanguageManager;
import home.utility.Bundles;

/**
 * 
 * the menu's buttons who a player can press.
 *
 */
public enum Buttons {

    /**
     * load an old game.
     */
    NEW_GAME,
    /**
     * create a new game.
     */
    LOAD_GAME,
    /**
     * close the application.
     */
    EXIT;

    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle(Bundles.BUTTON).getString(this.name());
    }
}
