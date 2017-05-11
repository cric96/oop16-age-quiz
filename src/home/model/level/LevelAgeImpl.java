package home.model.level;

/*the class that implements an age*/
//package-protected
final class LevelAgeImpl extends AbstractLevel implements Level.Age {
    private static final long serialVersionUID = 1L;
    //package protected
    LevelAgeImpl(final int currentLevel) {
        super(currentLevel);
        this.setAmountWithEnum(AgeEnum.values()[currentLevel - 1]);
    }
    @Override
    public boolean isUpgradable() {
        return (this.getCurrentLevel()) < this.getMaximumLevel();
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
        return AgeEnum.values()[this.getCurrentLevel() - 1].name();
    }
    @Override
    public int getMaximumLevel() {
        return AgeEnum.values().length;
    }

}
