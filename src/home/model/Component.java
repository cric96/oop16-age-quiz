package home.model;

import java.util.Optional;

/**
 * a component where you can change the age attach on an object.
*/
public interface Component {
    /**
     * @return
     *  the type of component
     */
    Class<?> getType();
    /**
     * 
    * @return
    *   the parent of this component type
     */
    Class<?> getParentType();
    /**
     * return the parent of this component
     * @return
     *  the Optional.of parent or 
     */
    Optional<?> getParent();
}
