package home.model.status;

/**
 * Models the different status of the kingdom.
 */
public interface Status {
    /**
     * 
     * @return
     *          the name of Status
     */
    String getName();
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
