package home.test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import home.model.query.Category;
import home.model.query.Query;
/**
 *A class to test what builder, loader and query should do.
 */
public class QueryTest {
    private static final String ANSWER = "wrong correct-answer!";
    private static final int LEVEL = 2;
    private static final String QUESTION = "To be or not to be??";
    /**
     * 
     */
    @Test
    public void builderTest() {
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

}
