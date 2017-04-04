package home.view.menu;

/**
 * 
 * the menu's buttons who a player can press.
 *
 */
public enum Buttons {

    /**
     * load an old game.
     */
    LOAD_GAME("Load Game"),
    /**
     * create a new game.
     */
    NEW_GAME("New Game"),
    /**
     * close the application.
     */
    EXIT("Esci");

    private final String text;

    String getText() {
        return this.text;
    }

    Buttons(final String text) {
        this.text = text;
    }
}
