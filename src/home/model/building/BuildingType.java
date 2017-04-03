package home.model.building;
import home.model.query.Category;
/**
 * define all type of building with an associated category.
 */
public enum BuildingType {
    /**
     * 
     */
    SCHOOL(Category.SCIENCE),
    /**
     * 
     */
    HOSPITAL(Category.MEDICINE),
    /**
     * 
     */
    BUILDING_SITE(Category.MANUFACTURING),
    /**
     * 
     */
    ACADEMY(Category.LIBERAL_ARTS),
    /**
     * 
     */
    SPORT_CENTER(Category.SPORT);
    private Category influezed;
    BuildingType(final Category category) {
        this.influezed = category;
    }
    Category getCategory() {
        return this.influezed;
    }
}
