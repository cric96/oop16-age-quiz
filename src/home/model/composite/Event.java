package home.model.composite;
/**
 * an event that can be send by object to another.
 * @param <E> the source of the event
 */
public interface Event <E> {
    /**
     * @return
     *  the name of the event
     */
    String getTypes();
    /**
     * 
     * @return
     *  the source of the event
     */
    E getSource();
}
