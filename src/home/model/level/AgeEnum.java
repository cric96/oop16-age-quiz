package home.model.level;
/**
 * the possible age in this contest.
 */
public enum AgeEnum {
    /**
     * 
     */
    ETA_DELLA_PIETRA(1000),
    /**
     * 
     */
    MEDIOEVO(2000),
    /**
     * 
     */
    RINASCIMENTO(3000);
    private final int experienceAmount;
    //package-protected
    /*it's enable only in this package because other object only want to know the experience associated with ad age*/
    int getExperience() {
        return this.experienceAmount;
    }
    AgeEnum(final int experienceAmount) {
        this.experienceAmount = experienceAmount;
    }
}
