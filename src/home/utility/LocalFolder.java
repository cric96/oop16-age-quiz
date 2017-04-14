package home.utility;
/**
 * define a constant to the use of file system.
 */
public enum LocalFolder {
    /**
     * 
     */
    LOCAL(System.getProperty("user.home")),
    /**
     * 
     */
    SEPARATOR(System.getProperty("path.separator")),
    /**
     * 
     */
    CONFIG_FOLDER(LOCAL.getInfo() + SEPARATOR.getInfo() + ".age-of-quiz-config"),
    /**
     * 
     */
    SAVE_FOLDER(CONFIG_FOLDER + "save");
    private String value;
    LocalFolder(final String value) {
        this.value = value;
    }
    /**
     * 
     * @return
     *  the path associated with a constant
     */
    public String getInfo() {
        return this.value;
    }
}
