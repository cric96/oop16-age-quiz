package home.model.quiz;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import home.model.status.StatusName;
import home.utility.Pair;

public class QuizGameImpl implements QuizGame {
    private final Quiz quizIterator;
    private Query currentQuery;
    private String currentAnswer;
    private int currentXP;
    private Set<Pair<StatusName, Integer>> statusScore;
    public QuizGameImpl(final Category cat, final int level) {
        this.quizIterator = new QuizImpl(cat, level);
    }

    @Override
    public Query showCurrentQuery() {
        this.currentQuery = this.quizIterator.next();
        return this.currentQuery;
    }

    @Override
    public void hitAnswer(String answer) {
        Objects.requireNonNull(answer);
        
        this.currentAnswer = answer;
    }

    @Override
    public boolean isAnswerCorrect() {
        return this.currentQuery.isAnswerCorrect(this.currentAnswer);
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
    public Set<Pair<StatusName, Integer>> getStatusScore() {
        // TODO Auto-generated method stub
        return null;
    }

}
