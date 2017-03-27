package home.model.building;

import home.model.Kingdom;
import home.model.composite.Component;
/**
 * A building that is influence by the action of a kingdom.
 */
public interface BuildingComposite extends ImmutableAgeBuilding.Container, Component<Kingdom> {

}
