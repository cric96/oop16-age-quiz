package home.model.building;

import home.model.Component;

/**
 * models a component influenced by age.
 */
public interface AgeComponent extends Component {
    /**
     * what to do when you go on the next age.
     */
    void nextAge();
}
