package home.model.composite;

import java.util.Set;

import home.model.utility.Pair;

/**
 * a container of component objects.
 */
public interface Composite {
    /**
     * get component by giving is type.
     * @param type
     *  the type of component
     * @param <Y> 
     *  the type of interface
     * @return
     *  a Set of component with type selected and a boolean that specify if a component is enable or not
     */
    <Y> Set<Pair<Y, Boolean>> getComponents(Class<Y> type);
    /**
     * add a component in the kingdom.
     * @param component
     *  the component that i want to add
     */
    void addComponent(Component<?> component);
}
