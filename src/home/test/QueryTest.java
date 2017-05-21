package home.test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import home.model.level.Level;
import home.model.query.Category;
import home.model.query.Query;
import home.model.query.QueryLoader;
/**
 *A class to test what builder, loader and query should do.
 */
public class QueryTest {
    private static final String ANSWER = "That's the question";
    private static final String ANSWER_2 = "May be or may not";
    private static final int LEVEL = 2;
    private static final int TEST_LEVEL = 10;
    private static final String QUESTION = "To be or not to be??";
    private static final String QUESTION_2 = "Am I wrong?";
    private static final int QUESTION2 = 1;
    private static final int QUESTION1 = 0;
    /**
     * 
     */
    @Test
    public void builderTest() {
        InitializeLanguage.initialize();
        final Query.Builder builder = Query.Builder.createBuilder();
        this.buildTest(builder);
        try {
            builder.addCorrectAnswer(ANSWER);
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        builder.addAnswer(ANSWER);
        builder.addCorrectAnswer(ANSWER);
        this.buildTest(builder);
        builder.addCategory(Category.LIBERAL_ARTS);
        builder.addDifficulty(LEVEL);
        builder.addQuestion(QUESTION);
        builder.build();
        this.buildTest(builder);
    }
    private void buildTest(final Query.Builder builder) {
        try {
            builder.build();
            fail();
        } catch (IllegalStateException e) {
            assertNotNull(e);
        }
    }
    /**
     * 
     */
    @Test
    public void loaderTest() {
        final QueryLoader ql = QueryLoader.getQueryLoader();
        final List<Query> list = ql.getQueries(Category.MEDICINE, Level.Building.createBuildingLevel());
        assertTrue(list.stream().allMatch(x -> x.getCategory() == Category.MEDICINE));
        assertTrue(list.stream().allMatch(x -> x.getDifficulty() == Level.Building.createBuildingLevel().getIncrementalLevel()));
        try {
            ql.getQueries(Category.LIBERAL_ARTS, Level.Building.restoreBuildingLevel(TEST_LEVEL,
                                                                                     Level.Building.INITIAL_MAX_LEVEL,
                                                                                     Level.Building.INITIAL_EXPERIACE_AMOUNT,
                                                                                     Level.Building.LEVEL_ADVANCE));
            fail();
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
    /**
     * 
     */
    @Test
    public void queryTest() {
        final List<Query> queryList = new ArrayList<>();
        queryList.add(Query.Builder.createBuilder()
                .addQuestion(QUESTION)
                .addAnswer(ANSWER)
                .addCorrectAnswer(ANSWER)
                .addCategory(Category.SCIENCE)
                .addDifficulty(LEVEL)
                .build());
        queryList.add(Query.Builder.createBuilder()
                .addQuestion(QUESTION_2)
                .addAnswer(ANSWER_2)
                .addAnswer(ANSWER)
                .addCorrectAnswer(ANSWER_2)
                .addCategory(Category.SCIENCE)
                .addDifficulty(LEVEL)
                .build());
        assertNotNull(queryList.get(QUESTION2).getCategory());
        assertFalse(queryList.get(QUESTION2).isAnswerCorrect(ANSWER));
        assertSame(queryList.get(QUESTION2).getAnswers().size(), 2);
        assertSame(queryList.get(QUESTION1).getQuestion(), QUESTION);
        try {
            queryList.get(QUESTION1).getAnswers().add(ANSWER_2);
            fail();
       } catch (UnsupportedOperationException e) {
           assertNotNull(e);
       }
    }

}
