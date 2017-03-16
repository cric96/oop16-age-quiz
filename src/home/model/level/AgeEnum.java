package home.model.level;
//package-protected
enum AgeEnum {
    ETA_DELLA_PIETRA(1000),
    MEDIOEVO(2000),
    RINASCIMENTO(3000);
    private final int experienceAmount;
    //package-protected
    int getExperience() {
        return this.experienceAmount;
    }
    AgeEnum(final int experienceAmount) {
        this.experienceAmount = experienceAmount;
    }
}
