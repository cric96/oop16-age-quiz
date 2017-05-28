package home.model.kingdom;

import java.io.Serializable;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import home.model.building.Building;
import home.model.composite.Component;
/**
 * a strategy to define if the kingdom can age-up or not.
*/
public interface AgeUpKingdomStrategy extends BooleanSupplier {
    /**
     * create an age strategy.
     * by default create a simple age strategy
     * @param type
     *  the type of strategy used
     * @param components
     *  the componenet attach on the kingdom
     * @return
     *  the strategy created
     */
    static AgeUpKingdomStrategy createStrategy(final AgeUpKingdomStrategy.Type type, final Set<Component<?>> components) {
        if (type == AgeUpKingdomStrategy.Type.ADVANCED) {
            final Set<Building> buildings = components.stream().filter(x -> Building.class.isAssignableFrom(x.getType()) && x.isEnable())
                                                               .map(x -> (Building) x)
                                                               .collect(Collectors.toSet());

            return (AgeUpKingdomStrategy & Serializable) () -> buildings.stream()
                                                                 .allMatch(x -> !x.getLevel().isUpgradable());
        }
        return (AgeUpKingdomStrategy & Serializable) () -> true;
    }
    /**
     * the different type of strategy.
     */
    enum Type {
        SIMPLE,
        ADVANCED;
    }
}
