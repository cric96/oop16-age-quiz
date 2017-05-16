package home.model.quiz;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;

import home.model.level.Level;
import home.model.query.Category;
import home.model.query.Query;
import home.model.query.QueryLoader;
import home.model.status.StatusName;
import home.utility.CList;
//package protected
abstract class AbstractQuizGame implements QuizGame {
    private static final int START_POSITION = 0;
    private final Iterator<Query> cQueries;
    private final Calculator calculator;
    private Query currentQuery;
    private Optional<String> currentAnswer;
    private int currentXP;
    private final Map<StatusName, Integer> statusScore;
    private final Category cat;
    private boolean stopQuiz;
    /**
     * 
     * @param cat
     * @param level
     */
    AbstractQuizGame(final Category cat, final Level level) {
        final List<Query> list = QueryLoader.getQueryLoader().getQueries(cat, level);
        cQueries = CList.createCList(list, START_POSITION).iterator();
        this.currentAnswer = Optional.empty();
        this.statusScore = cat.getStatusNames().stream().collect(Collectors.toMap(x -> x, x -> 0));
        this.currentQuery = this.cQueries.next();
        this.calculator = new MultiplierCalculator(new BasicCalculator(cat.getStatusNames()));
        this.cat = cat;
    }

    @Override
    public Query showCurrentQuery() {
        this.checkFinished();
        return this.currentQuery;
    }

    @Override
    public void hitAnswer(final String answer) {
        this.checkFinished();
        Objects.requireNonNull(answer);
        this.currentAnswer = Optional.of(answer);
        this.computeScore();
    }
    private void checkFinished() {
        if (this.isFinished()) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean isAnswerCorrect() {
        this.checkFinished();
        if (!this.currentAnswer.isPresent()) {
            throw new IllegalStateException("You must chose an answer");
        }
        return this.currentQuery.isAnswerCorrect(this.currentAnswer.get());
    }

    @Override
    public boolean isFinished() {
        return this.stopQuiz;
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
        this.checkFinished();
        this.currentQuery = this.cQueries.next();
    }
    @Override
    public void setFinished() {
        this.stopQuiz = true;
    }
    private void computeScore() {
        if (this.isAnswerCorrect()) {
            this.calculator.correct();
        } else {
            this.calculator.wrong();
        }
        this.currentXP += this.calculator.getXP();
        this.calculator.getStatusScore().forEach((x, y) -> this.statusScore.put(x, this.statusScore.get(x) + y));
    }
    @Override
    public Category getCategory() {
        return this.cat;
    }
}
