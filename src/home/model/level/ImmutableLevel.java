package home.model.level;


/**
 * a level that can't be upgrade.
 */
public interface ImmutableLevel {
    /**
     * tell if a level is upgradable.
     * @return
     *  true if is upgradable false otherwise
     */
    boolean isUpgradable();
    /**
     * 
     * @return
     *  an incremental value that define the current level
     */
    int getIncrementalLevel();
    /**
     * 
     * @return
     *  the experience amount to the next level
     *  throws illegalStateException if is not upgradable
     */
    int getExperienceAmount();
}
