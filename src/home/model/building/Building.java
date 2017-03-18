package home.model.building;

import home.model.level.Level;
import home.model.quiz.Category;
import home.model.utility.Pair;
/**
 * define an interface for building.
 */
public interface Building {
    /**
     * 
     * @return
     *  the name of building
     */
    String getName();
    /**
     * 
     * @return
     *  an incremental value that define current level
     */
    int getLevel();
    /*TODO attendere la costruzione di QuizGame*/
    /**
     * create a new quiz.
     */
    void startQuiz();
    /**
     * 
     * @return
     *  the category of this building
     */
    Category getInfluecedCategory();
    /**
     * go to the next level.
     * @param experience
     *  the experience amount 
     * @return
     *  true if the building level up false otherwise 
     */
    boolean levelUp(int experience);
    /**
     * 
     * @return
     *  true if the building is upgradable false otherwise
     */
    boolean isUpgradable();
    /**
     * models a building with an image.
     */
    interface Image extends Building {
        /**
         * 
         * @return
         *  the Image associated to the Building
         */
        Image getImage();
    }
    /**
     * models a building with an age.
     */
    interface Age extends Building {
        /**
         * go to the next age if it is possible
         * @param currentAge
         *  the next age to go
         * @return
         *  true if the building go on the next age false otherwise
         */
        boolean nextAge(int currentAge);
    }
}
