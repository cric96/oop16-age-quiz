package home.model.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
        this.quiz.add(new QueryBuilder()
                .addQuestion("Quante sono le note musicali?")
                .addAnswers(new HashSet<>(Arrays.asList("5", "6", "7", "9")))
                .addCorrectAnswer("7")
                .addCategory(Category.LIBERAL_ARTS)
                .addDifficulty(1)
                .build());
        this.quiz.add(new QueryBuilder()
                .addQuestion("Qual è la sigla del cloruro di sodio?")
                .addAnswers(new HashSet<>(Arrays.asList("NaCl", "Na3Cl2", "Na2Cl3", "Na4Cl4")))
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
