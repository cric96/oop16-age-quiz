package home.model.level;

import home.model.utility.Pair;

/**
 * Models the concept of level in a videogame.
 */
public interface Level {
    /**
     * go to the next level (if is possible).
     * throws illegalStateException if is on the maximum level
     * @param experienceAmount
     *  if the experienceAmount is not enough you can't pass the level
     * @return
     *  true is the level goes on the next level false otherwise 
     */
    boolean nextLevel(int experienceAmount);
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
    /**
     * a specific level of a building.
     */
    interface Building extends Level {
        /**
         * 
         * @return
         *  a level with a max level 
         */
        static Level.Building createBuildingLevel() {
            return new LevelBuildingImpl();
        }
        /**
         * change the maximum level of a level
         * @param level
         *      the new value, if is less then 0 throw new IllegalArgumentException
         *      if is less then the current level throw new IllegalArgumentException
         */
        void setMaxiumLevel(int level);
    }
    /**
     * define an age.
     */
    interface Age extends Level {
        static Level.Age createAgeLevel() {
            return new LevelAgeImpl();
        }
        /**
         * @return
         *  name of the age
         */
        String getLevelName();
    }
}
