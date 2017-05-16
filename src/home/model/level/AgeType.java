package home.model.level;

import java.util.Optional;

import home.utility.BundleLanguageManager;

/**
 * the possible age in this contest.
 */
public enum AgeType {
    /**
     * 
     */
    STONEAGE(new LevelAgeImpl(1, 1000, "STONEAGE")),
    /**
     * 
     */
    MIDDLEAGES(new LevelAgeImpl(2, 2000, "MIDDLEAGES")),
    /**
     * 
     */
    RENAISSANCE(new LevelAgeImpl(3, 4000, "RENAISSANCE"));
    private final Level.Age age;
    AgeType(final Level.Age age) {
        this.age = age;
    }
    Level.Age getAge() {
        return this.age;
    }
    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle("AgeBundle").getString(this.name());
    }

    private static final class LevelAgeImpl extends AbstractLevel implements Level.Age {
        private static final long serialVersionUID = 1L;
        private final String name;
        //package protected
        LevelAgeImpl(final int currentLevel, final int experience, final String name) {
            super(currentLevel, experience);
            this.name = name;
        }
        @Override
        public boolean isUpgradable() {
            return (this.getCurrentLevel()) < this.getReachMaximumLevel();
        }
        @Override
        public String getName() {
            return this.name;
        }
        @Override
        public int getReachMaximumLevel() {
            return AgeType.values().length;
        }
        @Override
        public Optional<Age> nextAge(final int experience) {
            if (this.canLevelUp(experience)) {
                return Optional.of(AgeType.values()[this.getCurrentLevel()].getAge());
            } else {
                return Optional.empty();
            }
        }
    }
}
