package home.model.building;
import home.model.level.ImmutableLevel;
import home.model.quiz.Category;
import home.model.quiz.QuizGame;
import home.model.composite.Composite;;

/**
 * a building that where you can't modify internal age.
 */
public interface ImmutableAgeBuilding {
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
    ImmutableLevel getLevel();
    /*TODO PENSA BENE A QUESTO METODO*/
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
     * @return
     *  return true if building level up false otherwise
     */
    boolean levelUp();
    /**
     * a non modified building composed with some AgeComponent.
     */
    interface Container extends ImmutableAgeBuilding, Composite { }
}
