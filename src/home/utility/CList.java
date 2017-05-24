package home.utility;

import java.util.Iterator;
import java.util.List;

/**
 * An immutable Cyclic list of elements of type X. 
 * A cyclic list is constructed from a standard list, but then you can access elements at any
 * non-negative position, which is done by moving back at the beginning as you reach the end.
 * @param <X>
 *  the element that contains the list
 */
public interface CList<X> {
    /**
     * Simple factory to create a new CList.
     * @param list
     *  the list that will be "changed" in a cyclic list
     * @param pos
     *  starting position of the cyclic list
     * @param <E>
     *  generic method in E
     * @return
     *  a cyclic list with the same elements of the list passed
     */
    static <E> CList<E> createCList(final List<E> list, final int pos) {
        return new CListImpl<E>(list, pos);
    }
    /**
     * @return the number of elements in the cyclic list not considering the cycle
     */
    int size();
    /**
     * @param pos
     *          the position of the wished element
     * @return the element in position pos, assuming any non-negative number is accepted 
     */
    X getElem(int pos);
    /**
     * @param x 
     *          the element wished
     * @return whether an element equal to x is contained somewhere in the cyclic list
     */
    boolean contains(X x);
    /**
     * @param x
     *          new element for the CList
     *@param pos
     *          where to add the new element
     * @return a new cyclic list, with element x added at position pos
     */
    CList<X> add(X x, int pos);
    /**
     * @param pos
     *          where the new clist will start
     * @return a new cyclic list, with same elements of this, but where first element is the one at position pos
     */
    CList<X> shift(int pos);

    /**
     * @return the ordered list of elements in the cyclic list
     */
    List<X> toList();
    /**
     * 
     * @return
     *  an iterator for the cyclic list
     */
    Iterator<X> iterator();
}