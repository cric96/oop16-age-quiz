package home.model.level;

import java.util.Optional;

import home.utility.Pair;
/*the class that implements an age*/
final class LevelAgeImpl extends AbstractLevel implements Level.Age {
    //package protected
    LevelAgeImpl() {
        super();
        this.setAmountWithEnum(AgeEnum.ETA_DELLA_PIETRA);
    }
    @Override
    public boolean isUpgradable() {
        return this.getCurrentLevel() < AgeEnum.values().length - 1;
    }

    @Override
    public Pair<String, Integer> getLevelInfo() {
        return Pair.createPair(AgeEnum.values()[this.getCurrentLevel()].toString(), this.getCurrentLevel());
    }

    @Override
    protected void goOnNextLevel() {
        this.setAmountWithEnum(AgeEnum.values()[this.getCurrentLevel()]);
    }
    private void setAmountWithEnum(final AgeEnum name) {
        this.setExperienceAmount(name.getExperience());
    }

}
