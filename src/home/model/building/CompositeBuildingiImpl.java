package home.model.building;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.Component;
import home.model.level.Level;
import home.model.quiz.Category;

final class CompositeBuildingiImpl extends AbstractBuilding implements Building.Composite {
    private final Set<AgeComponent> components;
    CompositeBuildingiImpl(final String name, final Level.Building level,
            final  Category category, final int currentAge) {
        super(name, level, category, currentAge);
        this.components = new HashSet<>();
    }

    @Override
    public Set<Component> getComponents() {
        return this.components.stream()
                              .<Component>map(x -> (Component) x)
                              .collect(Collectors.toSet());
    }

    @Override
    public void addComponent(final AgeComponent component) {
        Objects.requireNonNull(component);
        this.components.add(component);
    }
    @Override
    protected void advanceOnAge() {
        this.components.forEach(x -> x.nextAge());
    }
    @Override
    public Class<?> getType() {
        return Building.Composite.class;
    }
    /**
     * TODO DA FARE!
     */
    @Override
    public Class<?> getParentType() {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * TODO DA FARE!
     */

    @Override
    public Optional<?> getParent() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
