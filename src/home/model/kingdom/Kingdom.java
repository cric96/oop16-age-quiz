package home.model.kingdom;
import java.io.File;
import java.util.Map;
import java.util.Objects;

import com.google.gson.reflect.TypeToken;

import home.model.composite.Composite;
import home.model.level.Level;
import home.model.serializer.Serializer;
import home.model.status.StatusName;

/**
 * define the interface of a kingdom.
 */

public interface Kingdom extends Composite {
    /**
     * create a serializer used to save and restore the current kingdom.
     * @param file
     *  the file where the kingdom is located
     * @return
     *  the serializer
     */
    static Serializer<Kingdom> createSerializer(final File file) {
        Objects.requireNonNull(file);
        return Serializer.createJsonSerializer(file, new TypeToken<Kingdom>() { }.getType(), new KingdomAdapter());
    }
    /**
     * 
     * @return
     *  the level associated in this kingdom
     */
    Level.Age getAge();
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
    Map<StatusName, Integer> getStatusStatistic();
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
    /**
     * go on the next age.
     * update all component attach on kingdom
     * @throws IllegalStateException 
     *  if you can't go on next age
     */
    void nextAge();
    /**
     * tell if the kingdom can go on next age.
     * @return
     *  true if it can false otherwise 
     */
    boolean canUpgradeAge();
}
