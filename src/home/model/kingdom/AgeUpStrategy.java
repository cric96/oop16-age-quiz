package home.model.kingdom;

import java.io.Serializable;
import java.util.Set;
import java.util.function.BooleanSupplier;

import home.model.building.BuildingOfKingdom;
/**
 * a strategy to define if the kingdom can age up or not.
*/
public interface AgeUpStrategy extends BooleanSupplier {
    /**
     * create a simple strategy, return always true.
     * @return
     *  the strategy created
    */
    static AgeUpStrategy createSimple() {
        return (AgeUpStrategy & Serializable) () -> true;
    }
    /**create advance strategy that check all the building.
     * and if all building aren't upgradable the kingdom can't age up
     * @param buildings
     *  the building used to check
     * @return
     *  the strategy created
    */
    static AgeUpStrategy createBuildingCheck(final Set<BuildingOfKingdom> buildings) {
        return (AgeUpStrategy & Serializable) () -> buildings.stream()
                                                             .filter(x -> x.isEnable())
                                                             .allMatch(x -> !x.getLevel().isUpgradable());
    }
}
