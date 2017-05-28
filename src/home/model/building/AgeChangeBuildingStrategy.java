package home.model.building;

import java.io.Serializable;
import java.util.Optional;

import home.model.composite.Event;
import home.model.level.AgeType;
import home.model.level.Level;
/**
 * define what the building does when the age changes.
 */
public interface AgeChangeBuildingStrategy  {
    /**
     * what to do when the age changes.
     * @param event
     *  the value created
     * @param age
     *  the current age
     * @param current
     *  the current level of building
     * @return
     *  Optional.empty if the building is disabled, Optional.of level if the building is enabled
     */
    Optional<Level.Building> onAgeChange(Event.Age<?> event, AgeType age, Level.Building current);
    /**
     * 
     * @param type
     *  the type of strategy
     * @param building
     *  the name of building
     * @return
     *  the strategy created
     */
    static AgeChangeBuildingStrategy createStrategy(Type type, BuildingType building) {
        return (AgeChangeBuildingStrategy & Serializable) (e, a, l) -> {
            if (e.currentAge().ordinal() >= a.ordinal()) {
                return l.maxiumLevelchanged(l.getReachMaximumLevel() + 1);
            } else {
                return Optional.empty();
            }
        };
    }
    /**
     * the type of strategy.
     */
     enum Type {
        /**
         * simple strategy
         */
        SIMPLE;
    }
}
