package home.model.building;
import home.model.level.ImmutableLevel;
import home.model.query.Category;
import java.io.Serializable;

import home.model.composite.Composite;;

/**
 * a building that where you can't modify internal age.
 */
public interface ImmutableAgeBuilding extends  Serializable {
    /**
     * 
     * @return
     *  the name of building
     */
    BuildingType getName();
    /**
     * 
     * @return
     *  an incremental value that define current level
     */
    ImmutableLevel getLevel();
    /**
     * 
     * @return
     *  the category of this building
     */
    Category getInfluecedCategory();
    /**
     * go to the next level.
     * @throws IllegalStateException if it is call when the object can't level up
     * @return
     *  return true if building level up false otherwise
     */
    boolean levelUp();
    /**
     * a non modified building composed with some AgeComponent.
     */
    interface Container extends ImmutableAgeBuilding, Composite { }
}
