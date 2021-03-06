package home.model.quiz;

import home.model.level.Level;
import home.model.query.Category;
//package-protected
class QuizGameBasic extends AbstractQuizGame {
    private static final int LAST = 30;
    QuizGameBasic(final Category cat, final Level level) {
        super(cat, level);
    }
    @Override
    public int getQuizDuration() {
        return LAST;
    }

}
