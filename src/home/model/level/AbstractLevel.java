package home.model.level;

//package-protected
abstract class AbstractLevel implements Level {
    private int currentLevel;
    @Override
    public void nextLevel() {
        if (isUpgradable()) {
            this.currentLevel++;
        } else {
            throw new IllegalStateException();
        }
    }

    protected int getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public String toString() {
        return "[currentLevel=" + currentLevel + " upgradable=" + this.isUpgradable() + "]";
    }
}
