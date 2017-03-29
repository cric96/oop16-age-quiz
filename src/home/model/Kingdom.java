package home.model;

import home.model.composite.Composite;
import home.model.level.ImmutableLevel;
import home.model.status.StatusName;
import home.model.utility.Pair;


//TODO CONCLUDI TUTTA QUESTA INTERFACCIA
/**
 * define the interface of a kingdom.
 */

public interface Kingdom extends Composite {
    /**
     * 
     * @return
     *  the current age of the kingdom
     */
    String getAgeName();
    /**
     * 
     * @return
     *  the level associated in this kingdom
     */
    ImmutableLevel getAge();
    /**
     * @return
     *  the experience of the kingdom
     */
    int getExperienceAmount();
    /**
     * 
     * @param amount
     *  add experience in the kingdom
     */
    void addExperience(int amount);
    /**
     * 
     * @param amount
     *  decrease the experience in the kingdom
     */
    void decExperiene(int amount);
    /**
     * 
     * @return
     *  get the statistic of the status
     */
    Pair<StatusName, Integer> getStatusStatistic();
    /**
     * 
     * @param name
     *  the type of status
     * @param amount
     *  the amount that i want to change
     * @return
     *  true if the change is applied false otherwise
     *  throws an IllegalArgumentException if something goes wrong
     */
    boolean changeStatus(StatusName name, int amount);
}
