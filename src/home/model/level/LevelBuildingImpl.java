package home.model.level;

import home.model.level.Level.Building;
//package-protected
final class LevelBuildingImpl extends AbstractLevel implements Building {
    private static final long serialVersionUID = 1L;
    /*the initial max level of a building*/
    private static final int INITIAL_MAX_LEVEL = 2;
    private static final int INITIAL_EXPERIENCE = 1000;
    private int maxLevel;
    //package-protected
    LevelBuildingImpl() {
        super();
        this.setMaxiumLevel(INITIAL_MAX_LEVEL);
        this.setExperienceAmount(INITIAL_EXPERIENCE);
    }

    @Override
    public void setMaxiumLevel(final int level) {
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
        this.setExperienceAmount(this.getCurrentLevel() * (INITIAL_EXPERIENCE));
    }
}
