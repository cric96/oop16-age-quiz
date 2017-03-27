package home.model.building;

/**
 * factory to create Building.
 */
public class BuildingFactory {
    private BuildingFactory(){}
    private static final int INITIAL_AGE = 0;
    private static final BuildingFactory SINGLETON = new BuildingFactory();
    /**
     * @return the instance of factory
     */
    public static BuildingFactory get() {
        return BuildingFactory.SINGLETON;
    }
}
