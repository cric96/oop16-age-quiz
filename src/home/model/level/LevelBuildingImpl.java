package home.model.level;

import home.model.level.Level.Building;
import home.utility.Pair;
//package-protected
class LevelBuildingImpl extends AbstractLevel implements Building {
    /* TODO Usare un file per prendere la parola giusta riguardo al livello */
    private static final String LEVEL = "Livello";
    private int maxLevel;
    //package-protected
    LevelBuildingImpl(final int maxLevel) {
        this.setMaxiumLevel(maxLevel);
    }

    @Override
    public void setMaxiumLevel(final int level) {
        if (this.getCurrentLevel() < level) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean isUpgradable() {
        return this.maxLevel < this.getCurrentLevel();
    }

    @Override
    public Pair<String, Integer> getLevelInfo() {
        return Pair.createPair(LEVEL + this.getCurrentLevel(), this.getCurrentLevel());
    }

}
