package home.model.level;

import java.util.Optional;

/**
 * an interface to define an immutable level in the game.
 * each level has a successive level unlike the last one
 * every level has a set of properties
 */
public interface Level {
    /**
     * by default all the levels start with this value.
     */
    int INITIAL_LEVEL = 1;
    /**
     * tell if a level has a successive level or not.
     * @return
     *  true if has a successive false otherwise
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
     *  the experience amount to go on the successive level
     */
    int getExperienceAmount();
    /**
     * 
     * @return
     *  the maximum incremental value of the successive level
     */
    int getReachMaximumLevel();

    /**
     * The level of a building.
     * a building can change the maximum level in base of his age
     */
    interface Building extends Level {
        /**
         * by default all the levels have this initial max level.
         */
        int INITIAL_MAX_LEVEL = 2;
        /**
         * by default all the levels have this initial experience amount
         */
        int INITIAL_EXPERIACE_AMOUNT = 1000;
        /**
         * by default all the levels use this amount to increase 
         */
        int LEVEL_ADVANCE = 1000;
        /**
         * Static factory
         * create a level with initial values
         * @return
         *  a level of building
         */
        static Level.Building createBuildingLevel() {
            return new LevelBuildingImpl(INITIAL_LEVEL, INITIAL_MAX_LEVEL, INITIAL_EXPERIACE_AMOUNT, LEVEL_ADVANCE);
        }
        /**
         * Static factory
         * create a specific level
         * @param currentLevel
         *  the index of current level
         * @param maxLevel
         *  the max level that can reach
         * @param experince
         *  the experience to level up
         * @param levelAdvance
         *  an amount to define how to go on the next level
         * @return
         *  the level created
         */
        static Level.Building restoreBuildingLevel(final int currentLevel, final int maxLevel, final int experince, final int levelAdvance) {
            return new LevelBuildingImpl(currentLevel, maxLevel, experince, levelAdvance);
        }
        /**
         * get a level with a different maximum successive level
         * @param level
         *      the new value, if is less then 0 throw new IllegalArgumentException
         *      if is less then the current level throw new IllegalArgumentException
         * @return 
         *  a new level with a different maximum level
         */
        Optional<Level.Building> maxiumLevelchanged(int level);
        /**
         * get the successive level
         * @param experienceAmount
         *  the experience amount necessary to get the successive level
         * @return
         *  Optional.empty if the experience is not enough
         *  Optional.of level if the experience is enough
         */
        Optional<Level.Building> nextLevel(int experienceAmount);
    }
    /**
     * a specific level that define an age with the name of age.
     */
    interface Age extends Level {
        /**
         * Simple factory
         * create a Age an the initial time
         * @return
         *  an age created
         */
        static Level.Age createAgeLevel() {
            return restoreAgeLevel(INITIAL_LEVEL);
        }
        /**
         * Simple factory
         * create an Age with a specific index of age
         * @param currentAgeLevel
         *  the current index of age
         * @return
         *  the age created
         */
        static Level.Age restoreAgeLevel(final int currentAgeLevel) {
            return AgeType.values()[currentAgeLevel - 1].getAge();
        }
        /**
         * @return
         *  name of the age
         */
        String getName();
        /**
         * get the next age
         * @param experienceAmount
         *  the experience amount to take the next age
         * @return
         *  Optional.empty if the experience is not enough
         *  Optional.of age if the experience is enough
         */
        Optional<Level.Age> nextAge(int experienceAmount);
    }
}
