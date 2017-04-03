package home.model.building;
<<<<<<< HEAD
import home.model.query.Category;
=======
import home.model.level.AgeEnum;
import home.model.level.ImmutableLevel;
import home.model.quiz.Category;
>>>>>>> ac205f398766d231b7cebe85ef5ca038e718bae2
/**
 * define all type of building with an associated category.
 */
public enum BuildingType {
    /**
     * 
     */
    SCHOOL(Category.SCIENCE, AgeEnum.ETA_DELLA_PIETRA),
    /**
     * 
     */
    HOSPITAL(Category.MEDICINE, AgeEnum.ETA_DELLA_PIETRA),
    /**
     * 
     */
    BUILDING_SITE(Category.MANUFACTURING, AgeEnum.ETA_DELLA_PIETRA),
    /**
     * 
     */
    ACADEMY(Category.LIBERAL_ARTS, AgeEnum.MEDIOEVO),
    /**
     * 
     */
    SPORT_CENTER(Category.SPORT, AgeEnum.MEDIOEVO);
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
    AgeEnum getAgeEnable(){
        return this.ageEnable;
    }
}
