package home.model.building;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.composite.Component;
import home.model.composite.Composite;
import home.model.image.ImageComponent;
import home.model.level.Level;

/**
 * factory to create Building.
 */
public final class BuildingFactory {
    private static final BuildingFactory SINGLETON = new BuildingFactory();
    private BuildingFactory() { }
    /**
     * @return the instance of factory
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
    public BuildingComposite createSimpleBuilding(final BuildingType name) {
        return new BuildingImpl(Level.Building.createBuildingLevel(), name);
    }
    /**
     * create all type of building.
     * @return
     *  return the set of building create
     */
    public Set<BuildingComposite> createAllBuilding() {
        return Arrays.stream(BuildingType.values())
                     .<BuildingComposite>map(x -> this.createAdvanceBuilding(x, Level.Building.createBuildingLevel(), 
                             new HashSet<Component<Composite>>(Arrays.asList(ImageComponent.createComponent(x.name())))))
                     .collect(Collectors.toSet());
    }
    /**
     * create a building with specified information.
     * @param name
     *  the name of building
     * @param level
     *  the level of building
     * @param components
     *  the components that you want to attach on the building
     * @return
     *  the building created
     */
    public BuildingComposite createAdvanceBuilding(final BuildingType name, final Level.Building level, final Set<Component<Composite>>  components) {
        final BuildingComposite building = new BuildingImpl(level, name);
        components.forEach(x -> Component.compositeAttach(building, x));
        return building;
    }
}
