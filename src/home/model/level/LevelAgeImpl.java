package home.model.level;

import java.util.Optional;

import home.model.utility.Pair;
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
    protected void goOnNextLevel() {
        this.setAmountWithEnum(AgeEnum.values()[this.getCurrentLevel()]);
    }
    private void setAmountWithEnum(final AgeEnum name) {
        this.setExperienceAmount(name.getExperience());
    }
    @Override
    public String getLevelName() {
        return AgeEnum.values()[this.getCurrentLevel()].toString();
    }

}
