package home.model.composite;

import java.util.Set;

import home.utility.Pair;

/**
 * Component container.
 */
public interface Composite {
    /**
     * get component by giving is type.
     * @param type
     *  the type of component
     * @param <Y> 
     *  the type of interface
     * @return
     *  a Set of components with selected type and a boolean that specify if a component is enabled or not
     */
    <Y> Set<Pair<Y, Boolean>> getComponents(Class<Y> type);
    /**
     * get all the components attached on this composite.
     * @return
     *  the set of component
     */
    Set<Component<?>> getComponents();
    /**
     * add a component in the composite.
     * you can add only if it is already attach otherwise @throws IllegalStateException
     * @param component
     *  the component that i want to add
     */
    void addComponent(Component<?> component);
}
