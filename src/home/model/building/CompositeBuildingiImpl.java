package home.model.building;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<AgeComponent> getComponents() {
        return this.components.stream()
                              .map(x -> new BuildingComponentClone(x))
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
    /*In questo modo proteggo lo stato interno dell'oggetto altrimenti da fuori si potrebbe chiamare l'operazione nextAge*/
    private static class BuildingComponentClone implements AgeComponent {
        private final AgeComponent component;
        BuildingComponentClone(final AgeComponent component) {
            this.component = component;
        }
        @Override
        public Class<?> getType() {
            return this.component.getType();
        }
        @Override
        public void nextAge() {
            throw new IllegalStateException("cannot be called this operation out of building!");
        }
    }
}
