package home.utility;
/**
 * define a constant to the use of file system.
 */
public final class LocalFolder {
    /**
     * 
     */
    public static final String LOCAL = System.getProperty("user.home");
    /**
     * 
     */
    public static final String SEPARATOR = System.getProperty("file.separator");
    /**
     * 
     */
    public static final String CONFIG_FOLDER = LOCAL +  SEPARATOR + ".age-of-quiz-config";
    private LocalFolder() { }
}
