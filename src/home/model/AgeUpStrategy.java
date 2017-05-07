package home.model;

import java.io.Serializable;
import java.util.Set;
import java.util.function.BooleanSupplier;

import home.model.building.BuildingComposite;
/* a strategy to define if the kingdom can age up or not */
//package-protected
interface AgeUpStrategy extends BooleanSupplier {
    /*create a simple strategy, return always true */
    static AgeUpStrategy createSimple() {
        return (AgeUpStrategy & Serializable) () -> true;
    }
    /*create advance strategy that check all the building 
     * and if all building aren't upgradable the kingdom can't age up*/
    static AgeUpStrategy createBuildingCheck(final Set<BuildingComposite> buildings) {
        return (AgeUpStrategy & Serializable) () -> buildings.stream()
                                                              .allMatch(x -> !x.getLevel().isUpgradable());
    }
}
