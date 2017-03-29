package home.view.menu;

/**
 * 
 * the menu's buttons who a player can press
 *
 */
public enum MenuButtons {

    NEW_GAME("New Game"),
    LOAD_GAME("Load Game"),
    EXIT("Exit");

    private final String text;

    String getText() {
        return this.text;
    }

    MenuButtons(final String text) {
        this.text = text;
    }
}
