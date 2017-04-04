package home.model.composite;

class BaseEvent<E> implements Event<E> {
    private final E source;
    private final EventType type;
    BaseEvent(final E source, final EventType type) {
        this.source = source;
        this.type = type;
    }
    public String getTypes() {
        return this.type.toString();
    }
    public E getSource() {
        return this.source;
    }
}
