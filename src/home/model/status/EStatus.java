package home.model.status;
/**
 * Describe all type of Status in the videogame.
 */
public enum EStatus implements Status {
    HEALT("Healt"),
    LOGIC("Logic");
    private static final int MAX = 100;
    private static final int MIN = 0;
    private final String name;
    private int value;
    EStatus(final String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void addValue(final int value) {
        this.value += value;
        if (this.value > EStatus.MAX) {
            this.value = EStatus.MAX;
        }
    }

    @Override
    public void decValue(final int value) {
        this.value -= value;
        if (this.value < EStatus.MIN) {
            this.value = EStatus.MIN;
        }
    }

}
