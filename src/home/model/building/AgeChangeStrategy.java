package home.model.building;

import java.io.Serializable;
import java.util.Optional;

import home.model.composite.Event;
import home.model.level.AgeType;
/*what to do on level when the age change*/
import home.model.level.Level;
interface AgeChangeStrategy {
    /*return true if the age in the event is bigger than age*/
    Optional<Level.Building> onAgeChange(Event.Age<?> event, AgeType age, Level.Building current);
    static AgeChangeStrategy createBasic() {
        return (AgeChangeStrategy & Serializable) (e, a, l) -> {
            if (e.currentAge().ordinal() >= a.ordinal()) {
                return l.maxiumLevelchanged(l.getReachMaximumLevel() + 1);
            } else {
                return Optional.empty();
            }
        };
    }
}
