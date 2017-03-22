package home.model.level;

import java.util.Optional;

import home.model.level.Level.Building;
import home.model.utility.Pair;
//package-protected
final class LevelBuildingImpl extends AbstractLevel implements Building {
    /*the initial max level of a building*/
    private static final int INITIAL_MAX_LEVEL = 1;
    /* TODO Useare un file per associare ad ogni livello una certa esperienza (O qualcosa di simile)*/
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
    /* TODO implementa una versione più carina */
    @Override
    protected void goOnNextLevel() {
        this.setExperienceAmount(this.getCurrentLevel() * INITIAL_EXPERIENCE);
    }
}
