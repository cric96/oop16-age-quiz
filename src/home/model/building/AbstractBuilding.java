package home.model.building;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.Kingdom;
import home.model.composite.Component;
import home.model.composite.Event;
import home.model.composite.EventType;
import home.model.level.ImmutableLevel;
import home.model.level.Level;
import home.model.quiz.Category;
import home.model.quiz.QuizGame;
import home.model.utility.Utility;
//package-protected
abstract class AbstractBuilding implements BuildingComposite {
    /*TODO ricorda che devi trovare un modo per salire di livello in base all'era*/
    private final String name;
    private final Level.Building level;
    private final Category category;
    private final Set<Component<?>> components;
    private Optional<Kingdom> parent;
    AbstractBuilding(final String name, final Level.Building level, final Category category) {
        if (Utility.checkNullOb(category, level, name)) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.level = level;
        this.category = category;
        this.components = new HashSet<>();
        this.parent = Optional.empty();
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final ImmutableLevel getLevel() {
        return this.level;
    }
    /*TODO aspettare l'interfaccia di creazione*/
    @Override
    public QuizGame startQuiz() {
        return null;
    }
    @Override
    public final Category getInfluecedCategory() {
        return this.category;
    }

    @Override
    public boolean levelUp() {
        /*if i don't add a kingdom i can't level up!*/
        final Kingdom k = this.parent.orElseThrow(() -> new IllegalStateException());
        final int amount = this.level.getExperienceAmount();
        if (this.level.nextLevel(k.getExperienceAmount())) {
            k.decExperiene(amount);
            return true;
        }
        return false;
    }
    public final int getExperienceNecesary() {
        return this.level.getExperienceAmount();
    }
    @Override
    public final <Y> Set<Y> getComponents(final Class<Y> type) {
        return this.components.stream().filter(x -> x.getType() == type)
                                .map(x -> type.cast(x))
                                .collect(Collectors.toSet());
    }
    @Override
    public final Class<?> getType() {
        return ImmutableAgeBuilding.Container.class;
    }
    @Override
    public Optional<Kingdom> getParent() {
        return this.parent;
    }
    @Override
    public void attachOn(final Kingdom parent) {
       if (this.parent.isPresent()) {
           throw new IllegalStateException("the component is already attach");
       }
       this.parent = Optional.ofNullable(parent);
       parent.addComponent(this);
    }
    @Override
    public void update(final Event<?> event) {
        /*if the type is age change and */
        if (event.getTypes().equals(EventType.AGE_CHANGE.name())) {
            //if you want you can check if the source is correct
            this.components.forEach(x -> x.update(event));
            onAgeChange();
        }
    }
    @Override
    public void addComponent(final Component<?> component) {
        if (component.getParent() == Optional.empty()) {
            throw new IllegalStateException("call attach on component first!");
        }
        this.components.add(component);
    }
    @Override
    public String toString() {
        return "AbstractBuilding [name=" + name + ", level=" + level + ", category=" + category + "]";
    }
    protected abstract void onAgeChange();
}
