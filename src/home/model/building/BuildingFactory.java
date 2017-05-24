package home.model.building;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.composite.Component;
import home.model.image.ImageComponent;
import home.model.level.Level;

/**
 * factory to create Building.
 */
public final class BuildingFactory {
    private static final BuildingFactory SINGLETON = new BuildingFactory();
    private BuildingFactory() { }
    /**
     * @return 
     *  the instance of factory
     */
    public static BuildingFactory get() {
        return BuildingFactory.SINGLETON;
    }
    /**
     * create a simple building.
     * @param name
     *  the type of building
     * @return
     *  the building created
     */
    public BuildingOfKingdom createSimpleBuilding(final BuildingType name) {
        return new BuildingImpl(Level.Building.createBuildingLevel(), name);
    }
    /**
     * create all type of building.
     * @return
     *  return the set of building create
     */
    public Set<BuildingOfKingdom> createAllBuilding() {
        return Arrays.stream(BuildingType.values())
                     .<BuildingOfKingdom>map(x -> this.createAdvanceBuilding(x, Level.Building.createBuildingLevel()))
                     .collect(Collectors.toSet());
    }
    /**
     * create a building with specified information.
     * @param name
     *  the name of building
     * @param level
     *  the level of building
     * @return
     *  the building created
     */
    public BuildingOfKingdom createAdvanceBuilding(final BuildingType name, final Level.Building level) {
        final BuildingOfKingdom building = new BuildingImpl(level, name);
        Component.compositeAttach(building, ImageComponent.createComponent(name.name()));
        return building;
    }
}
