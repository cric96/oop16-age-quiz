package home.model.building;

import java.util.Optional;
import java.util.Set;

import home.model.level.Level;
import home.model.quiz.Category;
import home.model.quiz.QuizGame;
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
    /**
     * create a new quiz.
     * @return 
     *  the quiz created
     */
    QuizGame startQuiz();
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
     *  return the experiece used if level up Optional.empty otherwhise
     */
    Optional<Integer> levelUp(int experience);
    /**
     * 
     * @return
     *  true if the building is upgradable false otherwise
     */
    boolean isUpgradable();
    /**
     * go to the next age.
     */
    void nextAge();

    /**
     * a building with some component.
     */
    interface Composite extends Building {
        /**
         * @return
         *  all components attach on Building
         */
        Set<BuildingComponent> getComponents();
        /**
         * 
         * @param component
         *  add a component to a specific building
         */
        void addComponent(BuildingComponent component);
    }
}
