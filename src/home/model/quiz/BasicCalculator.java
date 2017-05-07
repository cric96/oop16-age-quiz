package home.model.quiz;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.status.StatusName;
/**
 * This implementation considers every status as the other: score in positive or negative will be the same for each status.
 */
//package-protected
class BasicCalculator implements Calculator {
    private static final int UPDATE_XP_BY_CORRECT_ANSWER = +10;
    private static final int UPDATE_XP_BY_WRONG_ANSWER = +0;
    private static final int UPDATE_STATUS_BY_CORRECT_ANSWER = +1;
    private static final int UPDATE_STATUS_BY_WRONG_ANSWER = -1; 
    private final Set<StatusName> status;
    private Optional<Boolean> isCorrect;
    BasicCalculator(final Set<StatusName> influencedStatus) {
        Objects.requireNonNull(influencedStatus);
        this.status = influencedStatus;
        this.isCorrect = Optional.empty();
    }

    @Override
    public void correct() {
        this.isCorrect = Optional.of(true);
    }

    @Override
    public void wrong() {
       this.isCorrect = Optional.of(false);
    }

    @Override
    public int getXP() {
        return this.isCorrect.orElseThrow(()-> new IllegalStateException()) ? UPDATE_XP_BY_CORRECT_ANSWER : UPDATE_XP_BY_WRONG_ANSWER;
    }

    @Override
    public Map<StatusName, Integer> getStatusScore() {
        final int value = this.isCorrect.orElseThrow(() -> new IllegalStateException()) ? UPDATE_STATUS_BY_CORRECT_ANSWER : UPDATE_STATUS_BY_WRONG_ANSWER;
        return this.status.stream().collect(Collectors.toMap(x -> x, x -> value));
    }

}
