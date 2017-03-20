package home.model.building;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.level.Level;

/**
 * factory to create Building.
 */
public class BuildingFactory {
    private static final int INITIAL_AGE = 0;
    private static final BuildingFactory SINGLETON = new BuildingFactory();
    /**
     * @return the instance of factory
     */
    public static BuildingFactory get() {
        return BuildingFactory.SINGLETON;
    }
    /**
     * create a simple Building.Composite.
     * @param buildingType 
     *  the type of building
     * @param level 
     *  the level of building
     * @param age 
     *  the age of building
     * @return
     *  the building created
     * 
     */
    public Building.Composite createSimpleBuilding(final BuildingType buildingType, final Level.Building level, final int age) {
        return new CompositeBuildingiImpl(buildingType.name(), level, buildingType.getCategory(), age);
    }

    /*TODO AD OGNI EDIFICIO SI DOVRà AGGIUNGERE LA SUA IMMAGINE INIZIALE*/
    /**
     * create all possible building with the associated type.
     * @return
     *  set of building create
     */
    public Set<Building.Composite> createBuildings() {
        return Arrays.stream(BuildingType.values())
              .map(x -> this.createSimpleBuilding(x, Level.Building.createBuildingLevel(), INITIAL_AGE))
              .collect(Collectors.toSet());
    }
}
