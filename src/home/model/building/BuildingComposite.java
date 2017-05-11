package home.model.building;

import home.model.composite.Component;
import home.model.kingdom.Kingdom;
/**
 * A building that is influence by the action of a kingdom.
 */
public interface BuildingComposite extends ImmutableAgeBuilding.Container, Component<Kingdom> {

}
