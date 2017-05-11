package home.model.building;

import java.io.Serializable;

import home.model.composite.Event;
import home.model.level.AgeEnum;
import home.model.level.Level;
/*what to do when the age change*/
interface AgeChangeStrategy {
    /*return true if the age in the event is bigger than age*/
    boolean onAgeChange(Event.Age<?> event, AgeEnum age);
    static AgeChangeStrategy createBasic() {
        return (AgeChangeStrategy & Serializable) (e, a) -> e.currentAge().ordinal() >= a.ordinal();
    }
    static AgeChangeStrategy createSimpleLevel(final Level.Building level) {
        return (AgeChangeStrategy & Serializable) (e, a) -> {
            final boolean checkAge = AgeChangeStrategy.createBasic().onAgeChange(e, a);
            if (checkAge) {
                level.setMaximumLevel(level.getIncrementalLevel() + 1);
            }
            return checkAge;
        };
    }
}
