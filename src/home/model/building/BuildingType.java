package home.model.building;
import home.model.quiz.Category;
/**
 * define all type of building with an associated category.
 */
public enum BuildingType {
    /**
     * 
     */
    HANDWOOD(Category.MANUFACTURING),
    /**
     * 
     */
    HOSPITAL(Category.MEDICINE);
    private Category influezed;
    BuildingType(final Category category) {
        this.influezed = category;
    }
    Category getCategory() {
        return this.influezed;
    }
}
