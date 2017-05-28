package home.model.level;

import java.io.Serializable;

//package-protected
abstract class AbstractLevel implements Level, Serializable {
    private static final long serialVersionUID = 1L;
    private final int currentLevel;
    private final int experienceAmount;
    AbstractLevel(final int currentLevel, final int experienceAmount) {
        if (currentLevel < 0 && experienceAmount < 0) {
            throw new IllegalArgumentException();
        }
        this.currentLevel = currentLevel;
        this.experienceAmount = experienceAmount;
    }
    protected boolean canLevelUp(final int experienceAmount) {
        if (experienceAmount < 0) {
            throw new IllegalArgumentException();
        }
        this.checkUpgradable();
        return checkAmout(experienceAmount);
    }
    @Override
    public int getExperienceAmount() {
        return this.experienceAmount;
    }
    protected int getCurrentLevel() {
        return this.currentLevel;
    }
    private boolean checkAmout(final int experienceAmount) {
        //here i can do the the check on experienceAmount but it is check before
        return experienceAmount - this.experienceAmount >= 0;
    }
    @Override
    public int getIncrementalLevel() {
        return this.getCurrentLevel();
    }
    @Override
    public String toString() {
        return "[currentLevel=" + currentLevel + " upgradable=" + this.isUpgradable() + "]";
    }
    private void checkUpgradable() {
        if (!isUpgradable()) {
            throw new IllegalStateException();
        }
    }
}
