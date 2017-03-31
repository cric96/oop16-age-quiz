package home.model.composite;

import java.util.Set;

/**
 * a container of component objects.
 */
public interface Composite {
    //TODO RICORDATI DI ASSOCIARE AD OGNI COMPONENT LA POSSIBILITà CHE SIA ABILITATO O MENO
    /**
     * get component by giving is type.
     * @param type
     *  the type of component
     * @param <Y> 
     *  the type of interface
     * @return
     *  a Set of component with type selected
     */
    <Y> Set<Y> getComponents(Class<Y> type);
    /**
     * add a component in the kingdom.
     * @param component
     *  the component that i want to add
     */
    void addComponent(Component<?> component);
}
