package home.model.status;
//package-protected
final class StatusImpl implements Status {

    private static final long serialVersionUID = 1L;
    private static final int MAX = 100;
    private static final int MIN = 0;
    private final StatusName name;
    private int value;
    //package-protected
    StatusImpl(final StatusName name) {
        this.name = name;
    }
    @Override
    public StatusName getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void addValue(final int value) {
        checkValue(value);
        this.value += value;
        if (this.value > StatusImpl.MAX) {
            this.value = StatusImpl.MAX;
        }
    }

    @Override
    public void decValue(final int value) {
        checkValue(value);
        this.value -= value;
        if (this.value < StatusImpl.MIN) {
            this.value = StatusImpl.MIN;
        }
    }
    /*Check if the value in input is correct or not */
    private void checkValue(final int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString() {
        return "StatusImpl [name=" + name + ", value=" + value + "]";
    }
}
