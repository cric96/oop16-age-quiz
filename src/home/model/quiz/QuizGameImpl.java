package home.model.quiz;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;

import home.model.query.Category;
import home.model.query.Query;
import home.model.status.StatusName;

//package protected
class QuizGameImpl implements QuizGame {
    private static final int UPDATE_XP_BY_CORRECT_ANSWER = +1;
    private static final int UPDATE_XP_BY_WRONG_ANSWER = +0;
    private static final int UPDATE_STATUS_BY_CORRECT_ANSWER = +1;
    private static final int UPDATE_STATUS_BY_WRONG_ANSWER = -1;
    private final Quiz quizIterator;
    private Query currentQuery;
    private Optional<String> currentAnswer;
    private int currentXP;
    private final Map<StatusName, Integer> statusScore;
    /**
     * 
     * @param cat
     * @param level
     */
    QuizGameImpl(final Category cat, final int level) {
        this.quizIterator = new QuizImpl(cat, level);
        this.currentAnswer = Optional.empty();
        this.statusScore = cat.getStatusNames().stream().collect(Collectors.toMap(x -> x, x -> 0));
        this.currentQuery = this.quizIterator.next();
    }

    @Override
    public Query showCurrentQuery() {
        return this.currentQuery;
    }

    @Override
    public void hitAnswer(final String answer) {
        Objects.requireNonNull(answer);
        this.currentAnswer = Optional.of(answer);
        this.computeScore();
    }

    @Override
    public boolean isAnswerCorrect() {
        if (!this.currentAnswer.isPresent()) {
            throw new IllegalStateException("You must chose an answer");
        }
        return this.currentQuery.isAnswerCorrect(this.currentAnswer.get());
    }

    @Override
    public boolean isFinished() {
        return !this.quizIterator.hasNext();
    }

    @Override
    public int getXP() {
        return this.currentXP;
    }

    @Override
    public Map<StatusName, Integer> getStatusScore() {
        return Collections.unmodifiableMap(this.statusScore);
    }

    @Override
    public void next() {
        if (!this.currentAnswer.isPresent()) {
            throw new IllegalStateException("You have to chose an answer to go on");
        }
        if (this.isFinished()) {
            throw new NoSuchElementException();
        }
        this.currentQuery = this.quizIterator.next();
    }
    //da estrarre la strategia
    private void computeScore() {
        if (this.isAnswerCorrect()) {
            this.currentXP = this.currentXP + UPDATE_XP_BY_CORRECT_ANSWER;
            this.statusScore.keySet().forEach(k -> statusScore.put(k, statusScore.get(k) + UPDATE_STATUS_BY_CORRECT_ANSWER));
        } else {
            this.currentXP = this.currentXP + UPDATE_XP_BY_WRONG_ANSWER;
            this.statusScore.keySet().forEach(k -> statusScore.put(k, statusScore.get(k) + UPDATE_STATUS_BY_WRONG_ANSWER));
        }
    }

}
