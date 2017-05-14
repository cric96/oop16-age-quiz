package home.model.building;

import home.model.composite.Component;
import home.model.kingdom.Kingdom;
/**
 * A building that is influence by the action of the kingdom.
 */
public interface BuildingOfKingdom extends Building.Container, Component<Kingdom> {

}
