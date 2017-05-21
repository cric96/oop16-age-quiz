package home.utility;
/**
 *  define all bundle.
 */
public enum Bundles {
    /**
     * the name of bundle where is located all string for the ages.
     */
    AGE("AgeBundle"),
    /**
     * the name of bundle where is located all string for the errors.
     */
    ERROR("ErrorBundle"),
    /**
     * the name of bundle where is located all string for the buttons.
     */
    BUTTON("ButtonBundle"),
    /**
     * the name of bundle where is located all string for the labels.
     */
    LABEL("LabelBundle"),
    /**
     * the name of bundle where is located all string for the statuses.
     */
    STATUS("StatusBundle"),
    /**
     * the name of bundle where is located all string for the buildings.
     */
    BUILDING("BuildingBundle"),
    /**
     * the name of bundle where is located the string to load the right file.
     */
    QUERY("QueryBundle");

    private final String bundleName;
    Bundles(final String bundleName) { 
        this.bundleName = bundleName;
    }; 
    @Override
    public String toString() {
        return this.bundleName;
    }
}
