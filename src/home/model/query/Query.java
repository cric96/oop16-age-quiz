package home.model.query;

import java.util.Set;
/**
 * Query represents the core of this videogame, it contains the questions and their own answers.
 *
 */
public interface Query {
    /**
     * This method push out question to be done.
     * @return String representing the current question.
     */
    String getQuestion();
    /**
     * We have a set of answers for each query, only one of these will be correct.
     * @return Set<String>, every String is an answer for the current query.
     */
    Set<String> getAnswers();
    /**
     * 
     * @param answer 
     * @return true if the answer passed is correct, false otherwise.
     */
    boolean isAnswerCorrect(String answer);
    /**
     * 
     * @return an incremental int depending on the difficulty of the query.
     */
    int getDifficulty();
    /**
     * it shows what is the Category for this query.
     * @return Category.
     */
    Category getCategory();
/**
 * Following the pattern Builder, this interface will create an implementation for Query.
 */
    interface Builder {
        static Builder createBuilder() {
            return new QueryBuilder();
        }
        /** 
         * @param question
         * @return Builder, using the fluent interface.
         */
        Builder addQuestion(String question);
        /** 
         * @param answers
         * @return Builder, using the fluent interface.
         */
        Builder addAnswer(String answer);
        /** 
         * @param correctAnswer
         * @return Builder, using the fluent interface.
         */
        Builder addCorrectAnswer(String correctAnswer);
        /** 
         * @param category
         * @return Builder, using the fluent interface.
         */
        Builder addCategory(Category category);
        /** 
         * @param difficulty
         * @return Builder, using the fluent interface.
         */
        Builder addDifficulty(int difficulty);
        /** 
         * @throws IllegalStateException if any previous method hasn't been called
         * @return QueryImpl.
         */
        QueryImpl build();
    }

}
