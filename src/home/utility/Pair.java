package home.utility;


/**
 * models a pair of object.
 * @param <X>
 *      the first type of the pair
 * @param <Y>
 *      the second type of the pair
 */
public interface Pair<X, Y> {
    /**
     * create a specific pair.
     * @param first
     *  the first object of a pair
     * @param second
     *  the second object of a pair
     * @param <Z>
     *  the first type of a pair
     * @param <U>
     *  the second type of a pair
     * @return
     *  the pair created
     */
    static <Z, U> Pair<Z, U>createPair(final Z first, final U second) {
        return new PairImpl<Z, U>(first, second);
    }
    /**
     * @return
     *  the first object
     */
    X getX();
    /**
     * @return
     *  the second object
     */
    Y getY();

}
