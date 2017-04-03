package home.model.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import home.model.query.Category;
import home.model.query.Query;
/**
 * 
 */
//package-protected
class QuizImpl implements Quiz {
    private final List<Query> quiz;
    private final Iterator<Query> quizIterator;
    /**
     * Class constructor.
     */
    //la classe dovrà ancora essere rivista per la gestione del caricamento delle domande da file
    QuizImpl(final Category cat, final int level) {
        this.quiz = new ArrayList<>();
        this.addQuery();
        this.quizIterator = quiz.iterator();
    }
    /**
     * add.
     */
    public void addQuery() {
        this.quiz.add(Query.Builder.createBuilder()
                .addQuestion("Quante sono le note musicali?")
                .addAnswer("7")
                .addCorrectAnswer("7")
                .addCategory(Category.LIBERAL_ARTS)
                .addDifficulty(1)
                .build());
        this.quiz.add(Query.Builder.createBuilder()
                .addQuestion("Qual è la sigla del cloruro di sodio?")
                .addAnswer("NaCl")
                .addCorrectAnswer("NaCl")
                .addCategory(Category.SCIENCE)
                .addDifficulty(1)
                .build());
    }
    @Override
    public boolean hasNext() {
        return this.quizIterator.hasNext();
    }

    @Override
    public Query next() {
        return this.quizIterator.next();
    }

}
