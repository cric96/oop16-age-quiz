package home.model.composite;

import home.model.level.AgeType;
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
    /**
     * an event when the age change.
     * @param <T>
     *  the source of event
     */
    interface Age<T> extends Event<T> {
        static <T> Event.Age<T> createEvent(T source, AgeType ageName) {
            return new AgeEvent<T>(new BaseEvent<T>(source, EventType.AGE_CHANGE), ageName);
        }
        /**
         * @return
         *      the age changed
         */
        AgeType currentAge();
    }
}
