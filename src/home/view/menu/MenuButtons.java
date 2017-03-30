package home.view.menu;

/**
 * 
 * the menu's buttons who a player can press.
 *
 */
public enum MenuButtons {

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
    EXIT("Exit");

    private final String text;

    String getText() {
        return this.text;
    }

    MenuButtons(final String text) {
        this.text = text;
    }
}
