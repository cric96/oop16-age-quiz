package home.model.level;

import home.utility.BundleLanguageManager;

/**
 * the possible age in this contest.
 */
public enum AgeEnum {
    /**
     * 
     */
    STONEAGE(1000),
    /**
     * 
     */
    MIDDLEAGES(2000),
    /**
     * 
     */
    RENAISSANCE(3000);
    private final int experienceAmount;
    //package-protected
    /*it's enable only in this package because other object only want to know the experience associated with ad age*/
    int getExperience() {
        return this.experienceAmount;
    }
    AgeEnum(final int experienceAmount) {
        this.experienceAmount = experienceAmount;
    }

    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle("AgeBundle").getString(this.name());
    }
}
