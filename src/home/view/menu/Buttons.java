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
    NEW_GAME("New Game"),
    /**
     * create a new game.
     */
    LOAD_GAME("Load Game"),
    /**
     * close the application.
     */
    EXIT("Exit");

    private final String text;

    /**
     * @return the name of button.
     */
    public String getText() {
        return this.text;
    }

    Buttons(final String text) {
        this.text = text;
    }
}
