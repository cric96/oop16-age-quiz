package home.utility;
/**
 *  define all bundle.
 */
public enum Bundles {
    /**
     * the name of bundle where are located all of the strings for the ages.
     */
    AGE("AgeBundle"),
    /**
     * the name of bundle where are located all of the strings for the errors.
     */
    ERROR("ErrorBundle"),
    /**
     * the name of bundle where are located all of the strings for the buttons.
     */
    BUTTON("ButtonBundle"),
    /**
     * the name of bundle where are located all of the strings for the labels.
     */
    LABEL("LabelBundle"),
    /**
     * the name of bundle where are located all of the strings for the statuses.
     */
    STATUS("StatusBundle"),
    /**
     * the name of bundle where are located all of the stringa for the buildings.
     */
    BUILDING("BuildingBundle"),
    /**
     * the name of bundle where are located all of the strings to load the right file.
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
