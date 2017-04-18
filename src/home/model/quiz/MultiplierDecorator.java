package home.model.quiz;

import java.util.Map;
import java.util.stream.Collectors;

import home.model.status.StatusName;

class MultiplierDecorator extends CalcDecorator {
    private static final int START_MULTIPLIER = 0;
    private int correctCount; 
    MultiplierDecorator(final Calculator calc) {
        super(calc);
        this.correctCount = START_MULTIPLIER;
    }
    @Override
    public void correct() {
       super.correct();
       this.correctCount++;
    }

    @Override
    public void wrong() {
        super.wrong();
        this.correctCount = START_MULTIPLIER;
    }
    private boolean isCountTooLow() {
        return this.correctCount == START_MULTIPLIER;
    }
    @Override
    public int getXP() {
        if (this.isCountTooLow()) {
            return super.getXP();
        }
        return super.getXP() * this.correctCount;
    }

    @Override
    public Map<StatusName, Integer> getStatusScore() {
       if (this.isCountTooLow()) {
           return super.getStatusScore();
       }
       return super.getStatusScore().entrySet().stream().collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue() * this.correctCount));
    }
}
