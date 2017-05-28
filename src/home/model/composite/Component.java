package home.model.composite;

import java.util.Optional;

/**
 * a component attached on an object.
 * @param <E> the parent type of component
*/
public interface Component <E> {
    /**
     * @return
     *  the type of component or what the component wraps
     */
    Class<?> getType();
    /**
     * return the parent of this component.
     * @return
     *  the Optional.of parent or Optional.Empty if the component is no attach on some 
     */
    Optional<E> getParent();
    /**
     * attach the component on a parent.
     * @param parent
     *  the parent on which the component is attached
     */
    void attachOn(E parent);
    /**
     * the composite sends a message to all of its components.
     * if the source of the event is invalid throws @IllegalSourceException
     * @param event
     *  the event of what I want
     * @throws IllegalStateException 
     *  if the component has already a parent
     */
    void update(Event<?> event);
    /**
     * check if a component is enable.
     * @return
     *  true if the component is enable false otherwise
     */
    boolean isEnable();
    /**
     * the right way to attach component to a composite.
     * @param composite
     *  the composite where the component want to be attach on
     * @param component
     *  the component to attach
     * @param <Y> 
     *  the type of composite
     */
    static <Y extends Composite> void compositeAttach(Y composite, Component<Y> component) {
        component.attachOn(composite);
        composite.addComponent(component);
    }
}
