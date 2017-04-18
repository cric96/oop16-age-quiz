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
        return new CompositeBuildingiImpl(Level.Building.createBuildingLevel(), name);
    }
    /**
     * create all type of building.
     * @return
     *  return the set of building create
     */
    public Set<BuildingComposite> createAllBuilding() {
        return Arrays.stream(BuildingType.values())
                     .map(x -> this.createSimpleBuilding(x))
                     .peek(x -> {
                         //creo un'immagine con il nome dell'edificio
                         final ImageComponent image = ImageComponent.createComponent(x.getName().name());
                         Component.compositeAttach(x, image);
                     })
                     .collect(Collectors.toSet());
    }
}
