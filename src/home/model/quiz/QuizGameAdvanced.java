package home.model.quiz;

import home.model.level.ImmutableLevel;
import home.model.query.Category;
//package-protected
class QuizGameAdvanced extends AbstractQuizGame {
    private static final int LAST = 30;
    private static final int DIVIDEND = 10;
    private final int level;

    QuizGameAdvanced(final Category cat, final ImmutableLevel level) {
        super(cat, level);
        this.level = level.getIncrementalLevel();
    }

    @Override
    public int getQuizDuration() { 
        return LAST + DIVIDEND / this.level;
    }

}
