package home.model.level;

import home.utility.Pair;

class LevelAgeImpl extends AbstractLevel implements Level.Age {

    @Override
    public boolean isUpgradable() {
        return this.getCurrentLevel() < AgeEnum.values().length - 1;
    }

    @Override
    public Pair<String, Integer> getLevelInfo() {
        return Pair.createPair(AgeEnum.values()[this.getCurrentLevel()].toString(), this.getCurrentLevel());
    }

}
