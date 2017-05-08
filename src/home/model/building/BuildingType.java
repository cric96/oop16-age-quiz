package home.model.building;
import home.model.query.Category;
import home.utility.BundleLanguageManager;
import home.model.level.AgeEnum;
/**
 * define all type of building with an associated category.
 */
public enum BuildingType {
    /**
     * 
     */
    SCHOOL(Category.SCIENCE, AgeEnum.STONEAGE),
    /**
     * 
     */
    HOSPITAL(Category.MEDICINE, AgeEnum.STONEAGE),
    /**
     * 
     */
    BUILDING_SITE(Category.MANUFACTURING, AgeEnum.STONEAGE),
    /**
     * 
     */
    ACADEMY(Category.LIBERAL_ARTS, AgeEnum.MIDDLEAGES),
    /**
     * 
     */
    SPORT_CENTER(Category.SPORT, AgeEnum.MIDDLEAGES);
    private Category influezed;
    private final AgeEnum ageEnable;
    BuildingType(final Category category, final AgeEnum ageEnable) {
        this.ageEnable = ageEnable;
        this.influezed = category;
    }
    //package-protected
    Category getCategory() {
        return this.influezed;
    }
    //package-protected
    AgeEnum getAgeEnable() {
        return this.ageEnable;
    }

    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle("BuildingBundle").getString(this.name());
    }
}
