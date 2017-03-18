package home.model.level;

import java.util.Optional;

//package-protected
abstract class AbstractLevel implements Level {
    private int currentLevel;
    private int experienceAmount;
    @Override
    public boolean nextLevel(final int experienceAmount) {
        this.checkUpgradable();
        if (checkAmout(experienceAmount)) {
            this.currentLevel++;
            goOnNextLevel();
            return true;
        } else {
            return false;
        }
    }
    @Override
    public int getExperienceAmount() {
        this.checkUpgradable();
        return this.experienceAmount;
    }
    /*Set the experience amount for the next level*/
    protected void setExperienceAmount(final int experienceAmount) {
        this.experienceAmount = experienceAmount;
    }
    protected int getCurrentLevel() {
        return this.currentLevel;
    }
    private boolean checkAmout(final int experienceAmount) {
        //here i can do the the check on experienceAmount but it is check before
        return experienceAmount - this.experienceAmount > 0;
    }
    private void checkUpgradable() {
        if (!isUpgradable()) {
            throw new IllegalStateException();
        }
    }
    //set of operation to do when a level pass on the other
    protected abstract void goOnNextLevel();
    @Override
    public String toString() {
        return "[currentLevel=" + currentLevel + " upgradable=" + this.isUpgradable() + "]";
    }
}
