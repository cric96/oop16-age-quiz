package home.model.building;
import home.model.level.ImmutableLevel;
import home.model.query.Category;

import home.model.composite.Composite;;

/**
 * a general type of building.
 */
public interface Building {
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
     */
    void levelUp();
    /**
     * 
     * @return
     *  true if the building can level up false otherwise
     */
    boolean canLevelUp();
    /**
     * a building that can contains different type of component.
     */
    interface Container extends Building, Composite { }
}
