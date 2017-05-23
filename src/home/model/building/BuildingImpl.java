package home.model.building;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

import home.model.composite.AbstractComposite;
import home.model.composite.Event;
import home.model.kingdom.Kingdom;
import home.model.level.AgeType;
import home.model.level.Level;
import home.model.query.Category;
import home.utility.Utility;
//package-protected
final class BuildingImpl extends AbstractComposite implements BuildingOfKingdom, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int FIRST_AGE = 0;
    private final BuildingType type;
    private Level.Building level;
    private final AgeChangeBuildingStrategy strategy;
    //i can't use Optional<Kingdom> because it can't be saved
    private Kingdom parent;
    private boolean enable;
    BuildingImpl(final Level.Building level, final BuildingType type) {
        if (Utility.checkNullOb(level, type)) {
            throw new IllegalArgumentException();
        }
        this.type = type;
        this.level = level;
        this.enable = type.getAgeEnable() == AgeType.values()[FIRST_AGE];
        this.strategy = AgeChangeBuildingStrategy.createBasic();
    }

    @Override
    public BuildingType getName() {
        return this.type;
    }

    @Override
    public Level getLevel() {
        return this.level;
    }
    @Override
    public Category getInfluecedCategory() {
        return this.type.getCategory();
    }
    @Override
    public boolean canLevelUp() {
        final int expKingdom = this.getParent().orElseThrow(() -> new IllegalStateException()).getExperienceAmount();
        return this.level.getExperienceAmount() <= expKingdom && this.level.isUpgradable();
    }
    @Override
    public void levelUp() {
        /*if i don't add a kingdom i can't level up!*/
        this.getParent().orElseThrow(() -> new IllegalStateException());
        final int amount = this.level.getExperienceAmount();
        this.level = this.level.nextLevel(this.parent.getExperienceAmount()).orElseThrow(() -> new IllegalStateException());
        this.parent.decExperiene(amount);
    }
    public int getExperienceNecesary() {
        return this.level.getExperienceAmount();
    }
    @Override
    public Class<?> getType() {
        return Building.Container.class;
    }
    @Override
    public Optional<Kingdom> getParent() {
        return Optional.ofNullable(this.parent);
    }
    @Override
    public void attachOn(final Kingdom parent) {
        Objects.requireNonNull(parent);
        //if is already attach on a object it can't attach in other composite
       if (this.getParent().isPresent()) {
           throw new IllegalStateException("the component is already attach");
       }
       this.parent = parent;
       parent.addComponent(this);
    }
    @Override
    public void update(final Event<?> event) {
        Objects.requireNonNull(event);
        /*if the type is age change and */
        if (Event.Age.class.isAssignableFrom(event.getClass())) {
            //if you want you can check if the source is correct
            //i can do cast because i'm sure that the event is associated with age
            if (this.isEnable()) {
                this.getComponents().forEach(x -> x.update((Event.Age<?>) event));
            }
            final Optional<Level.Building> next = this.strategy.onAgeChange(Event.Age.class.cast(event), this.type.getAgeEnable(), this.level);
            if (next.isPresent()) {
                this.level = next.get();
                this.enable = true;
            }
        }
    }
    public boolean isEnable() {
        return enable;
    }
    @Override
    public String toString() {
        return "BuildingImpl [name=" + type.name() + ", level=" + level + ", category=" + type.getCategory() + "]";
    }
}
