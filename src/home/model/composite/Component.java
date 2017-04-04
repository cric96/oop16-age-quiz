package home.model.composite;

import java.util.Optional;

/**
 * a component attach on an object.
 * @param <E> the parent type of component
*/
public interface Component <E> {
    /*AGGIUNGI IL FATTO CHE UN COMPONENT POSSA ESSERE O NON ESSERE ABILITATO*/
    /**
     * @return
     *  the type of component or what the component wrap
     */
    Class<?> getType();
    /**
     * return the parent of this component.
     * @return
     *  the Optional.of parent or Optional.Empty if the component is no attach on some 
     */
    Optional<E> getParent();
    /**
     * attach the component in a parent.
     * @param parent
     *  the parent where the component is attach
     */
    void attachOn(E parent);
    /**
     * the composite send a message on all of its components.
     * if the source of event is invalid throws @IllegalSourceException
     * @param event
     *  the event of what i want
     */
    void update(Event<?> event);
    /**
     * check if a component is enable.
     * @return
     *  true if the component is enable false otherwise
     */
    boolean isEnable();
 
}
