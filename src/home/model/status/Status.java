package home.model.status;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Models the different status of the kingdom.
 */
public interface Status extends Serializable {
    /**
     * 
     * create a simple status.
     * @param name
     *  the name of status
     * @return
     *  the status created
     */
    static Status createSimpleStatus(final StatusName name) {
        return new StatusImpl(name);
    }
    /**
     * create a set of all statuses in the game.
     * @return
     *  the set of statuses
     */
    static Set<Status> createStatuses() {
        return Arrays.stream(StatusName.values()).map(x -> new StatusImpl(x))
                                                 .collect(Collectors.toSet());
    }
    /**
     * create a simple status with initial value.
     * @param name
     *  the name of status
     * @param initialValue
     *  the initial value of status
     * @return
     *  the status created
     */
    static Status createStatusWithValue(final StatusName name, final int initialValue) {
        final Status s = new StatusImpl(name);
        s.addValue(initialValue);
        return s;
    }
    /**
     * 
     * @return
     *          the name of Status
     */
    StatusName getName();
    /**
     * 
     * @return
     *          the value between 1 and 100 
     *          of the status
     */
    int getValue();
    /**
     * increase the value of the status.
     * @param value
     *  the value to add at the status
     */
    void addValue(int value);
    /**
     * decrease the value of the status.
     * @param value
     *  the value to decrease at the status
     */
    void decValue(int value);
}
