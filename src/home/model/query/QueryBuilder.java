package home.model.query;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import home.model.query.Query.Builder;
//package-protected
class QueryBuilder implements Builder {
    private String question;
    private final Set<String> answers;
    private String correctAnswer;
    private Category category;
    private Integer difficulty;
    private boolean built;
    QueryBuilder() {
        this.answers = new HashSet<>();
    }
    @Override
    public Builder addQuestion(final String question) {
        Objects.requireNonNull(question);
        this.question = question;
        return this;
    }

    @Override
    public Builder addAnswer(final String answer) {
        Objects.requireNonNull(answers);
        this.answers.add(answer);
        return this;
    }

    @Override
    public Builder addCorrectAnswer(final String correctAnswer) {
        Objects.requireNonNull(correctAnswer);
        if (!this.answers.contains(correctAnswer)) {
            throw new IllegalArgumentException("This answer is not in the possible answers");
        }
        this.correctAnswer = correctAnswer;
        return this;
    }

    @Override
    public Builder addCategory(final Category category) {
        Objects.requireNonNull(category);
        this.category = category;
        return this;
    }

    @Override
    public Builder addDifficulty(final int difficulty) {
        Objects.requireNonNull(difficulty);
        this.difficulty = difficulty;
        return this;
    }

    @Override
    public Query build() {
        if (this.question == null || this.answers == null || this.correctAnswer == null
                || this.category == null || this.difficulty == null) {
            throw new IllegalStateException();
        }
        if (this.built) {
            throw new IllegalStateException("This object already exists");
        }
        this.built = true;
        return new QueryImpl(question, answers, correctAnswer, category, difficulty);
    }

}
