package home.model.composite;

import home.model.level.AgeType;
class AgeEvent<T> implements Event.Age<T> {
    private final Event<T> base;
    private final AgeType name;
    AgeEvent(final Event<T> base, final AgeType currentLevel) {
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
    public AgeType currentAge() {
        return this.name;
    }
}
