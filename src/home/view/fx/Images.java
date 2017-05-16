package home.view.fx;

/**
 * enum used to load images inside a fx Scene.
 */
public enum Images {
    /**
     * menu background path.
     */
    MENU_BACKGROUND("/images/backgroundMenu.jpg"),

    /**
     * profile locked image path.
     */
    PROFILE_IMAGE_LOCK("/images/lockedProfile.png"),

    /**
     * profile unlocked image path.
     */
    PROFILE_IMAGE_UNLOCK("/images/unlockedProfile.png"),

    /**
     * profile empty image path.
     */
    PROFILE_IMAGE_EMPTY("/images/emptyProfile.png"),

    /**
     * home button image path.
     */
    BACK_HOME_PICTURE("/images/backHomeButton.png"),

    /**
     * stats icon.
     */
    STATS_ICON("/images/statsIcon.png"),

    /**
     * world background image.
     */
    WORLD_BACKGROUND("/images/worldBackground.jpg"),

    /**
     * X exit.
     */
    X_CROSS("/images/cros.png"),

    /**
     * 
     */
    CLOUD_PANE("/images/cloud.png");

    private String path;

    Images(final String path) {
        this.path = path;
    }

    /**
     * @return path of selected image.
     */
    public String getPath() {
        return this.path;
    }
}
