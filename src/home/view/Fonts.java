package home.view;

/**
 * list of possible fonts inside the game.
 */
public enum Fonts {

    /**
     * Courier new.
     */
    COURIER_NEW("/fonts/Courier_New.ttf"),

    /**
     * faith collapsing.
     */
    FAITH_COLLAPSING("/fonts/Faith_Collapsing.ttf");

    private String pathFont;

    Fonts(final String path) {
        this.pathFont = path;
    }

    /**
     * @return the for path.
     */
    public String getFontPath() {
        return this.pathFont;
    }
}
