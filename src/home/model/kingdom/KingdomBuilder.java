package home.model.kingdom;

import home.model.composite.Component;
import home.model.level.Level;
import home.model.status.Status;
/**
 * a builder for kingdom.
 */
public interface KingdomBuilder {
    /**
     * 
     * @return
     *  a new KingdomBuilder
     */
    static KingdomBuilder createBuilder() {
        return new KingdomBuilderImpl();
    }
    /**
     * set the age of kingdom.
     * @param age
     *  the age that you want to set
     * @return
     *  the builder
     */
    KingdomBuilder setAge(Level.Age age);
    /**
     * 
     * @param experience
     *  the experience that you want to set
     * @return
     *  the builder
     */
    KingdomBuilder setExperience(int experience);
    /**
     * @param status
     *  the status that you want to add
     * @return
     *  the builder
     */
    KingdomBuilder addStatus(Status status);
    /**
     * add a component to the kingdom.
     * @param component
     *  the component that you want to add
     * @return
     *  the builder
     */
    KingdomBuilder addComponent(Component<? super Kingdom> component);
    /**
     * set the strategy used to check when the kingdom try to age up.
     * @param strategy
     *  the strategy 
     * @return
     *  the builder
     */
    KingdomBuilder addStrategy(AgeUpKingdomStrategy.Type strategy);
    /**
     * create the kingdom.
     * @return
     *  the kingdom created
     */
    Kingdom build();
}
