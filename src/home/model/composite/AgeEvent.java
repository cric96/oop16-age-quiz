package home.model.composite;

import home.model.level.AgeEnum;
import home.model.level.ImmutableLevel;

class AgeEvent<T> implements Event.Age<T>{
    private final Event<T> base;
    private final AgeEnum name;
    AgeEvent(final Event<T> base, final AgeEnum currentLevel){
        this.base = base;
        this.name = currentLevel;
    }
    public String getTypes() {
        return base.getTypes();
    }

    @Override
    public T getSource() {
        return base.getSource();
    }

    @Override
    public AgeEnum currentAge() {
        return this.name;
    }
}
