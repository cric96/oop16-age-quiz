package home.model.level;

/*the class that implements an age*/
final class LevelAgeImpl extends AbstractLevel implements Level.Age {
    private static final long serialVersionUID = 1L;
    //package protected
    LevelAgeImpl() {
        super();
        this.setAmountWithEnum(AgeEnum.ETA_DELLA_PIETRA);
    }
    @Override
    public boolean isUpgradable() {
        return (this.getCurrentLevel()) < AgeEnum.values().length;
    }

    @Override
    protected void goOnNextLevel() {
        this.setAmountWithEnum(AgeEnum.values()[this.getCurrentLevel() - 1]);
    }
    private void setAmountWithEnum(final AgeEnum name) {
        this.setExperienceAmount(name.getExperience());
    }
    @Override
    public String getLevelName() {
        return AgeEnum.values()[this.getCurrentLevel() - 1].toString();
    }

}
