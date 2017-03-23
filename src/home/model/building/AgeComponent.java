package home.model.building;
/**
 * models a component of a building.
 */
public interface AgeComponent {
    /**
     * @return
     *  the type of component
     */
    Class<?> getType();
    /**
     * what to do when you go on the next age.
     */
    void nextAge();
}
