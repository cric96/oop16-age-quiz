package home.utility;

/**
 * some method that can be useful.
 */
public final class Utility {
    private Utility() { }
    /**
     * check if some object in input is null.
     * @param objects
     *  the array of object
     * @return
     *  true if one of these is null
     */
    public static boolean checkNullOb(final Object ... objects) {
        for (final Object o : objects) {
            if (o == null) {
                return true;
            }
        }
        return false;
    }
}
