package home.model.building;
import home.model.query.Category;
import home.utility.BundleLanguageManager;
import home.model.level.AgeType;
/**
 * define all type of building with an associated category.
 */
public enum BuildingType {
    /**
     * 
     */
    SCHOOL(Category.SCIENCE, AgeType.STONEAGE),
    /**
     * 
     */
    HOSPITAL(Category.MEDICINE, AgeType.STONEAGE),
    /**
     * 
     */
    BUILDING_SITE(Category.MANUFACTURING, AgeType.STONEAGE),
    /**
     * 
     */
    ACADEMY(Category.LIBERAL_ARTS, AgeType.MIDDLEAGES),
    /**
     * 
     */
    SPORT_CENTER(Category.SPORT, AgeType.MIDDLEAGES);
    private Category influezed;
    private final AgeType ageEnable;
    BuildingType(final Category category, final AgeType ageEnable) {
        this.ageEnable = ageEnable;
        this.influezed = category;
    }
    //package-protected
    Category getCategory() {
        return this.influezed;
    }
    //package-protected
    AgeType getAgeEnable() {
        return this.ageEnable;
    }

    @Override
    public String toString() {
        return BundleLanguageManager.get().getBundle("BuildingBundle").getString(this.name());
    }
}
