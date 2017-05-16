package home.model.level;

import java.util.Optional;

import home.model.level.Level.Building;
//package-protected
final class LevelBuildingImpl extends AbstractLevel implements Building {
    private static final long serialVersionUID = 1L;
    private final int experienceAdvance;
    private final int maxLevel;
    //package-protected
    LevelBuildingImpl(final int currentLevel, final int initialMaxLevel, final int experieceAmount, final int experienceAdvance) {
        super(currentLevel, experieceAmount);
        this.maxLevel = initialMaxLevel;
        this.experienceAdvance = experienceAdvance;
    }

    @Override
    public Optional<Level.Building> maxiumLevelchanged(final int level) {
        if (this.getReachMaximumLevel() > level) {
            throw new IllegalArgumentException();
        }
        return Optional.of(new LevelBuildingImpl(this.getCurrentLevel(), level, this.getExperienceAmount(), this.experienceAdvance));
    }

    @Override
    public boolean isUpgradable() {
        return this.maxLevel > this.getCurrentLevel();
    }
    @Override
    public int getReachMaximumLevel() {
        return this.maxLevel;
    }

    @Override
    public Optional<Building> nextLevel(final int experienceAmount) {
        if (this.canLevelUp(experienceAmount)) {
            return Optional.of(new LevelBuildingImpl(this.getCurrentLevel() + 1, 
                                         this.maxLevel,
                                         (this.getCurrentLevel() + 1) * (this.experienceAdvance),
                                         this.experienceAdvance));
        } else {
            return Optional.empty();
        }
    }

}
