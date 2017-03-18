package home.model.utility;

import org.junit.Test;

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
        return new Pair<Z, U>() {
            private final Z x = first;
            private final U y = second;
            @Override
            public Z getX() {
                return this.x;
            }

            @Override
            public U getY() {
                return this.y;
            }

            @Override
            public String toString() {
                return "[" + first.toString() + " - " + second.toString() + "]";
            }
        };
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
