package home.model.level;

import home.model.level.Level.Building;
//package-protected
final class LevelBuildingImpl extends AbstractLevel implements Building {
    private static final long serialVersionUID = 1L;
    private final int experienceAdvance;
    private int maxLevel;
    //package-protected
    LevelBuildingImpl(final int currentLevel, final int initialMaxLevel, final int experieceAmount, final int experienceAdvance) {
        super(currentLevel);
        this.setMaximumLevel(initialMaxLevel);
        this.setExperienceAmount(experieceAmount);
        this.experienceAdvance = experienceAdvance;
    }

    @Override
    public void setMaximumLevel(final int level) {
        if (this.getCurrentLevel() > level) {
            throw new IllegalArgumentException();
        }
        this.maxLevel = level;
    }

    @Override
    public boolean isUpgradable() {
        return this.maxLevel > this.getCurrentLevel();
    }
    /* TODO try to find another solution */
    @Override
    protected void goOnNextLevel() {
        this.setExperienceAmount(this.getCurrentLevel() * (this.experienceAdvance));
    }

    @Override
    public int getMaximumLevel() {
        return this.maxLevel;
    }

}
