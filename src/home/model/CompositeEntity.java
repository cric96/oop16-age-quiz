package home.model;

import java.util.Set;

import home.model.building.AgeComponent;

/**
 * an entity composed some component.
 * @param <E> the type of component
 */
public interface CompositeEntity <E extends Component> {
    /**
     * @return
     *  all components attach on entity
     */
    Set<? extends Component> getComponents();
    /**
     * 
     * @param component
     *  add a component to a specific building
     */
    void addComponent(E component);
}
