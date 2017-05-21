package home.model.kingdom;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import home.model.composite.Component;
import home.model.composite.Event;
import home.model.level.AgeType;
import home.model.level.Level.Age;
import home.model.status.Status;

final class KingdomBuilderImpl implements KingdomBuilder {
    private Age age;
    private final Set<Component<? super Kingdom>> components;
    private final Set<Status> statuses;
    private int experience;
    private boolean created;
    private AgeUpKingdomStrategy.Type strategy;
    KingdomBuilderImpl() {
        this.components = new HashSet<>();
        this.statuses = new HashSet<>();
        this.created = false;
    }
    @Override
    public KingdomBuilder setAge(final Age age) {
        Objects.requireNonNull(age);
        this.checkBuilded();
        this.age = age;
        return this;
    }

    @Override
    public KingdomBuilder setExperience(final int experience) {
        this.checkBuilded();
        if (experience < 0) {
            throw new IllegalArgumentException();
        }
        this.experience = experience;
        return this;
    }

    @Override
    public KingdomBuilder addStatus(final Status status) {
        Objects.requireNonNull(status);
        this.checkBuilded();
        this.statuses.add(status);
        return this;
    }

    @Override
    public KingdomBuilder addComponent(final Component<? super Kingdom> component) {
        Objects.requireNonNull(component);
        this.checkBuilded();
        this.components.add(component);
        return this;
    }

    @Override
    public KingdomBuilder addStrategy(final AgeUpKingdomStrategy.Type strategy) {
        Objects.requireNonNull(strategy);
        this.strategy = strategy;
        return this;
    }
    @Override
    public Kingdom build() {
        if (this.statuses.isEmpty()) {
            throw new IllegalStateException("you cannot create a kingdom without statuses");
        }
        if (this.age == null || this.strategy == null) {
            throw new IllegalStateException("you must add all argument");
        }
        this.checkBuilded();
        this.created = true;
        final Kingdom king = new KingdomImpl(this.statuses, this.age, strategy);
        this.components.forEach(x -> x.attachOn(king));
        this.normalizeAge(king);
        king.addExperience(this.experience);
        return king;
    }
    //to turn back all component into the correct age
    private void normalizeAge(final Kingdom king) {
       final AgeType age = AgeType.valueOf(this.age.getName());
       for (int i = 1; i <= age.ordinal(); i++) {
           final Event.Age<Kingdom> e = Event.Age.createEvent(king, AgeType.values()[i]);
           this.components.forEach(x -> x.update(e));
       }
    }
    private void checkBuilded() {
        if (this.created) {
            throw new IllegalStateException();
        }
    }
}
