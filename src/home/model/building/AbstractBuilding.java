package home.model.building;

import java.io.Serializable;
import java.util.Optional;

import home.model.Kingdom;
import home.model.composite.AbstractComposite;
import home.model.composite.Event;
import home.model.level.ImmutableLevel;
import home.model.level.Level;
import home.model.query.Category;
import home.utility.Utility;
//package-protected
abstract class AbstractBuilding extends AbstractComposite implements BuildingComposite, Serializable {
    private static final long serialVersionUID = 1L;
    private final BuildingType type;
    private final Level.Building level;
    //i can't use Optional<Kingdom> because it can't be saved
    private Kingdom parent;
    AbstractBuilding(final Level.Building level, final BuildingType type) {
        if (Utility.checkNullOb(level, type)) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.level = level;
    }

    @Override
    public final BuildingType getName() {
        return this.type;
    }

    @Override
    public final ImmutableLevel getLevel() {
        return this.level;
    }
    @Override
    public final Category getInfluecedCategory() {
        return this.type.getCategory();
    }
    @Override
    public boolean canLevelUp() {
        final int expKingdom = this.getParent().orElseThrow(() -> new IllegalStateException()).getExperienceAmount();
        return this.level.getExperienceAmount() < expKingdom && this.level.isUpgradable();
    }
    @Override
    public void levelUp() {
        /*if i don't add a kingdom i can't level up!*/
        this.getParent().orElseThrow(() -> new IllegalStateException());
        final int amount = this.level.getExperienceAmount();
        if (this.level.nextLevel(this.parent.getExperienceAmount())) {
            this.parent.decExperiene(amount);
        } else {
            throw new IllegalStateException();
        }
    }
    public final int getExperienceNecesary() {
        return this.level.getExperienceAmount();
    }
    @Override
    public final Class<?> getType() {
        return ImmutableAgeBuilding.Container.class;
    }
    @Override
    public Optional<Kingdom> getParent() {
        return Optional.ofNullable(this.parent);
    }
    @Override
    public void attachOn(final Kingdom parent) {
        //if is already attach on a object it can't attach in other composite
       if (this.getParent().isPresent()) {
           throw new IllegalStateException("the component is already attach");
       }
       this.parent = parent;
       parent.addComponent(this);
    }
    //TEMPLATE METHOD
    @Override
    public void update(final Event<?> event) {
        /*if the type is age change and */
        if (Event.Age.class.isAssignableFrom(event.getClass())) {
            //if you want you can check if the source is correct
            //i can do cast because i'm sure that the event is associated with age
            if (this.isEnable()) {
                this.getComponents().forEach(x -> x.update((Event.Age<?>) event));
            }
            onAgeChange((Event.Age<?>) event);
        }
    }
    @Override
    public String toString() {
        return "AbstractBuilding [name=" + type.name() + ", level=" + level + ", category=" + type.getCategory() + "]";
    }
    protected abstract void onAgeChange(Event.Age<?> event);
    protected BuildingType getBuildingType() {
        return this.type;
    }
    protected Level.Building getBuildingLevel() {
        return this.level;
    }
}
