package home.model.building;

import java.util.Set;

import home.model.CompositeEntity;
import home.model.level.ImmutableLevel;
import home.model.quiz.Category;
import home.model.quiz.QuizGame;
/**
 * define an interface for building.
 */
public interface Building extends ImmutableAgeBuilding{
    /**
     * go to the next age.
     */
    void nextAge();

    /**
     * a building with some component.
     */
    interface Composite extends Building, ImmutableAgeBuilding.Composite, AgeComponent { }
}
