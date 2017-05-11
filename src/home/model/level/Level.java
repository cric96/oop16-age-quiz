package home.model.level;

/**
 * an interface to define a level in the game.
 * a level could be upgrade with some experience
 * every level has a specific experience to level up
 */
public interface Level extends ImmutableLevel {
    /**
     * by default this is the level with all level start.
     */
    int INITIAL_LEVEL = 1;
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
     * The level of a building.
     * a building can change the maximum level in base of his age
     */
    interface Building extends Level {
        /**
         * by default all level building have this initial max level.
         */
        int INITIAL_MAX_LEVEL = 2;
        /**
         * by default all level building have this initial experience amount
         */
        int INITIAL_EXPERIACE_AMOUNT = 1000;
        /**
         * by default all level when increase use this amount 
         */
        int LEVEL_ADVANCE = 1000;
        /**
         * Simple factory
         * create a level with initial value
         * @return
         *  a level with a max level 
         */
        static Level.Building createBuildingLevel() {
            return new LevelBuildingImpl(INITIAL_LEVEL, INITIAL_MAX_LEVEL, INITIAL_EXPERIACE_AMOUNT, LEVEL_ADVANCE);
        }
        /**
         * Simple factory
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
         */
        static Level.Building restoreBuildingLevel(final int currentLevel, final int maxLevel, final int experince, final int levelAdvance) {
            return new LevelBuildingImpl(currentLevel, maxLevel, experince, levelAdvance);
        }
        /**
         * change the maximum level of a level
         * @param level
         *      the new value, if is less then 0 throw new IllegalArgumentException
         *      if is less then the current level throw new IllegalArgumentException
         */
        void setMaximumLevel(int level);
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
         * @return
         */
        static Level.Age restoreAgeLevel(final int currentAgeLevel) {
            return new LevelAgeImpl(currentAgeLevel);
        }
        /**
         * @return
         *  name of the age
         */
        String getLevelName();
    }
}
