package home.model.quiz;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//this class implements a specific query
//package-protected
final class QueryImpl implements Query {
    private final String question;
    private final Set<String> answers;
    private final String correctAnswer;
    private final Category category;
    private final int difficulty;
    QueryImpl(final String question, final Set<String> answers, final String correctAnswer,
               final Category category, final int difficulty) {
        this.question = question;
        this.answers = new HashSet<>(answers);
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }
    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public Set<String> getAnswers() {
        return Collections.unmodifiableSet(this.answers);
    }

    @Override
    public boolean isAnswerCorrect(final String answer) {
        Objects.requireNonNull(answer);
        return this.correctAnswer.equalsIgnoreCase(answer); 
    }

    @Override
    public int getDifficulty() {
        return this.difficulty;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

}
