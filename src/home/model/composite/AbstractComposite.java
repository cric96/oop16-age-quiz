package home.model.composite;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * a skeleton of a composite.
 */
public abstract class AbstractComposite implements Composite {
    private final Set<Component<?>> components;
    /**
     * a basic constructor to create a composite.
     */
    public AbstractComposite() {
        this.components = new HashSet<>();
    }
    @Override
    public <Y> Set<Y> getComponents(final Class<Y> type) {
       return this.components.stream().filter(x -> x.getType() == type)
                             .map(x -> type.cast(x))
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
