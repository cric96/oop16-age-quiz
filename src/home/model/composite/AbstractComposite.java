package home.model.composite;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.utility.Pair;
/**
 * a skeleton of a composite that could be save .
 */
public abstract class AbstractComposite implements Composite, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final Set<Component<?>> components;
    /**
     * a basic constructor to create a composite.
     */
    public AbstractComposite() {
        this.components = new HashSet<>();
    }
    @Override
    public <Y> Set<Pair<Y, Boolean>> getComponents(final Class<Y> type) {
       return this.components.stream().filter(x -> type.isAssignableFrom(x.getType()))
                             .map(x -> Pair.createPair(type.cast(x), x.isEnable()))
                             .collect(Collectors.toSet());
    }

    @Override
    public void addComponent(final Component<?> component) {
        if (component.getParent() == Optional.empty()) {
            throw new IllegalStateException("call attach on component first!");
        }
        this.components.add(component);
    }
    /**
     * allow to get all component in a child-class.
     * @return
     *  the set of component
     */
    protected Set<Component<?>> getComponents() {
        return this.components;
    }

}
